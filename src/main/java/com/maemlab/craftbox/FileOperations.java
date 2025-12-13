package com.maemlab.craftbox;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class FileOperations {
	private FileOperations() {
	}

	//*************************
	//*  Filename operations  *
	//*************************

	/**
	 * Returns the filename without extension
	 *
	 * @param f the file
	 * @return the name of the file without the extension
	 */
	public static String getFileNameWithoutExtension(File f) {
		return getFileNameWithoutExtension(f.getName());
	}

	/**
	 * Returns the filename without extension
	 *
	 * @param name the name of the file as string
	 * @return the name of the file without the extension
	 */
	public static String getFileNameWithoutExtension(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	//*************************
	//*   Text File Reading   *
	//*************************

	/**
	 * Reads each line of a small file denoted by its path into a ArrayList of String, using UTF-8 encoding.
	 *
	 * @param file the path of the file
	 * @return a List of String representing the lines of the file
	 * @throws IOException
	 */
	public static List<String> readSmallTextFileToList(String file) throws IOException {
		return readSmallTextFileToList(file, StandardCharsets.UTF_8);
	}

	/**
	 * Reads each line of a small file denoted by its URL into a List of String
	 *
	 * @param file the URL of the file
	 * @return a List of String representing the lines of the file
	 * @throws IOException
	 */
	public static List<String> readSmallTextFileToList(URL file) throws IOException {
		return readSmallTextFileToList(file.getPath().substring(1));
	}

	/**
	 * Reads each line of a small file denoted by its path into a ArrayList of String
	 *
	 * @param file the path of the file
	 * @param encoding the encoding of the file
	 * @return a List of String representing the lines of the file
	 * @throws IOException
	 */
	public static List<String> readSmallTextFileToList(String file, Charset encoding) throws IOException {
		Path path = Paths.get(file);
		return Files.readAllLines(path, encoding);
	}

	/**
	 * Reads a small text file into a String using UTF-8 encoding
	 *
	 * @param file the path of the file
	 * @return a String representing all the lines of the file
	 * @throws IOException
	 */
	public static String readSmallTextFile(String file) throws IOException {
		return readSmallTextFile(file, StandardCharsets.UTF_8);
	}

	/**
	 * Reads each line of a small file denoted by its URL into a String
	 *
	 * @param file the path of the file
	 * @return a String representing all the lines of the file
	 * @throws IOException
	 */
	public static String readSmallTextFile(String file, Charset encoding) throws IOException {
		List<String> l = readSmallTextFileToList(file, encoding);
		StringBuilder sb = new StringBuilder();
		for (String s : l) {
			sb.append(s.trim()).append("\n");
		}
		return sb.toString();
	}

	//********************************
	//*  Properties file operations  *
	//********************************

	/**
	 * Reads a properties file into a Properties object
	 *
	 * @param path the path of the file to read
	 * @return the Properties object
	 *
	 * @throws IOException
	 */
	public static Properties readPropertiesFile(String path) throws IOException {
		Properties p = new Properties();
		try (FileInputStream fis = new FileInputStream(path)) {
			p.load(fis);
		}
		return p;
	}

	/**
	 * Writes a Properties object into a properties file
	 *
	 * @param p    the properties to be written
	 * @param path the path of the file to write
	 *
	 * @throws IOException
	 */
	public static void writePropertiesFile(Properties p, String path) throws IOException {
		try (FileWriter fw = new FileWriter(path); BufferedWriter bw = new BufferedWriter(fw)) {
			p.store(bw, null);
		}
	}

//	public static JSONObject loadJson(String path) throws IOException {
//		return new JSONObject(readSmallTextFile(path));
//	}

	//****************************
	//*  File system operations  *
	//****************************

	/**
	 * Creates a directory if it doesn't already exist
	 *
	 * @param dir the path of the directory to create
	 * @throws IOException
	 */
	public static void createDirectory(String dir) throws IOException {
		var dirPath = Paths.get(dir);
		if (!Files.exists(dirPath)) {
			Files.createDirectory(dirPath);
		}
	}

	/**
	 * Copies a file from source to destination if the destination doesn't exist
	 *
	 * @param inputFile the path of the source file
	 * @param outputFile the path of the destination file
	 * @throws IOException
	 */
	public static void copyFile(String inputFile, String outputFile) throws IOException {
		Path outputPath = Paths.get(outputFile);
		if (!Files.exists(outputPath)) {
			try (InputStream is = new FileInputStream(inputFile)) {
				Files.copy(is, outputPath);
			}
		}
	}

	// TODO
	// deleteFile/deleteFiles
	// deleteDir
	// deleteDir&Files
	// justStem
	// createTempFile
	// createTempDir

}
