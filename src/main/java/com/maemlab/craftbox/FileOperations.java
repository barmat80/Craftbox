package com.maemlab.craftbox;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public final class FileOperations {
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
	 * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
	 */
	public static List<String> readSmallTextFileToList(String file) throws IOException {
		return readSmallTextFileToList(file, StandardCharsets.UTF_8);
	}

	/**
	 * Reads each line of a small file denoted by its URL into a List of String
	 *
	 * @param file the URL of the file
	 * @return a List of String representing the lines of the file
	 * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
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
	 * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
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
	 * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
	 */
	public static String readSmallTextFile(String file) throws IOException {
		return readSmallTextFile(file, StandardCharsets.UTF_8);
	}

	/**
	 * Reads each line of a small file denoted by its URL into a String
	 *
	 * @param file the path of the file
	 * @return a String representing all the lines of the file
	 * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
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
	 * @throws IOException if an error occurred when reading from the input stream.
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
	 * @throws IOException if writing this property list to the specified output stream throws an IOException
	 */
	public static void writePropertiesFile(Properties p, String path) throws IOException {
		try (FileWriter fw = new FileWriter(path); BufferedWriter bw = new BufferedWriter(fw)) {
			p.store(bw, null);
		}
	}

	//********************************
	//*  JSON file operations  *
	//********************************

	//	public static JSONObject loadJson(String path) throws IOException {
//		return new JSONObject(readSmallTextFile(path));
//	}

	public static void saveJsonStrToFile(Path path, String jsonStr) throws IOException {
		if(path.getParent() != null) {
			Files.createDirectories(path.getParent());
		}
		Files.writeString(path, jsonStr, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
	}

	//****************************
	//*  File system operations  *
	//****************************

	/**
	 * Copies a file from source to destination if the destination doesn't exist
	 *
	 * @param inputFile the path of the source file
	 * @param outputFile the path of the destination file
	 * @throws IOException if an I/O error occurs when reading or writing
	 */
	public static void copyFile(String inputFile, String outputFile) throws IOException {
		Path outputPath = Paths.get(outputFile);
		if (!Files.exists(outputPath)) {
			try (InputStream is = new FileInputStream(inputFile)) {
				Files.copy(is, outputPath);
			}
		}
	}

	/**
	 * Creates a directory if it doesn't already exist
	 *
	 * @param dir the path of the directory to create
	 * @throws IOException if an I/O error occurs or the parent directory does not exist
	 */
	public static void createDirectory(String dir) throws IOException {
		var dirPath = Paths.get(dir);
		if (!Files.exists(dirPath)) {
			Files.createDirectory(dirPath);
		}
	}

	/**
	 * Creates a temporary directory in the specified directory with the given prefix.
	 *
	 * @param path the path to the directory in which to create the temporary directory
	 * @param prefix the prefix string to be used in generating the directory's name
	 * @return the path of the newly created temporary directory
	 * @throws IOException if an I/O error occurs or the parent directory does not exist
	 */
	public static Path createTempDirectory(String path, String prefix) throws IOException {
		return createTempDirectory(Paths.get(path), prefix);
	}

	/**
	 * Creates a temporary directory in the specified directory with the given prefix.
	 *
	 * @param path the path to the directory in which to create the temporary directory
	 * @param prefix the prefix string to be used in generating the directory's name
	 * @return the path of the newly created temporary directory
	 * @throws IOException if an I/O error occurs or the parent directory does not exist
	 */
	public static Path createTempDirectory(Path path, String prefix) throws IOException {
		return Files.createTempDirectory(path, prefix);
	}

	/**
	 * Creates a temporary directory in the specified directory with the given prefix,
	 * returning the path as a String.
	 *
	 * @param path the path to the directory in which to create the temporary directory
	 * @param prefix the prefix string to be used in generating the directory's name
	 * @return the absolute path of the newly created temporary directory as a String
	 * @throws IOException if an I/O error occurs or the parent directory does not exist
	 */
	public static String createTempDirectoryAsString(String path, String prefix) throws IOException {
		return createTempDirectory(Paths.get(path), prefix).toString();
	}

	/**
	 * Creates a temporary directory in the specified directory with the given prefix,
	 * returning the path as a String.
	 *
	 * @param path the path to the directory in which to create the temporary directory
	 * @param prefix the prefix string to be used in generating the directory's name
	 * @return the absolute path of the newly created temporary directory as a String
	 * @throws IOException if an I/O error occurs or the parent directory does not exist
	 */
	public static String createTempDirectoryAsString(Path path, String prefix) throws IOException {
		return createTempDirectory(path, prefix).toString();
	}


	public static void deleteFile(String file) throws IOException {
		deleteFile(Paths.get(file));
	}

	public static void deleteFile(File file) throws IOException {
		deleteFile(file.toPath());
	}

	public static void deleteFile(Path file) throws IOException {
		Files.delete(file);
	}

	public static void deleteFiles(String[] files) throws IOException {
		for(String file : files) {
			deleteFile(Paths.get(file));
		}
	}

	public static void deleteFiles(File[] files) throws IOException {
		for(File file : files) {
			deleteFile(file.toPath());
		}
	}

	public static void deleteFiles(List<File> files) throws IOException {
		for(File file : files) {
			deleteFile(file.toPath());
		}
	}

	public static List<File> listFiles(String dir) throws IOException {
		return listFiles(Paths.get(dir));
	}

	/**
	 * Lists all files in the specified directory and its subdirectories recursively.
	 * The returned list is sorted in natural order.
	 *
	 * @param dir the directory to search for files
	 * @return a sorted list of all regular files found in the directory tree
	 * @throws IOException if an I/O error occurs when accessing the directory
	 */
	public static List<File> listFiles(Path dir) throws IOException {
		try (Stream<Path> stream = Files.walk(dir)){
			return stream.filter(Files::isRegularFile)
						 .map(Path::toFile)
						 .sorted()
						 .toList();
		}
	}

	/**
	 * Lists all files with the specified extension in the directory and its subdirectories recursively.
	 * The returned list is sorted in natural order.
	 *
	 * @param dir the directory to search for files
	 * @param extension the file extension to filter by (e.g., ".txt", ".java")
	 * @return a sorted list of all regular files with the given extension found in the directory tree
	 * @throws IOException if an I/O error occurs when accessing the directory
	 */
	public static List<File> listFilesByExtension(Path dir, String extension) throws IOException {
		try (Stream<Path> stream = Files.walk(dir)){
			return stream.filter(Files::isRegularFile)
						 .filter(path -> path.getFileName().toString().endsWith(extension))
						 .map(Path::toFile)
						 .sorted()
						 .toList();
		}
	}

	// TODO
	// deleteDir
	// fileExists
	// deleteDir&Files
}