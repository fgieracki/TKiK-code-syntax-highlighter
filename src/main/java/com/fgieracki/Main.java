package com.fgieracki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputFileName = args.length >= 1 ? args[0] : "src/main/resources/input.cpp";
        String outputFileName = args.length >= 2 ? args[1] : "src/main/resources/output.html";

        File inputFile = new File(inputFileName);
        File outputFile = new File(outputFileName);

        TokenDB tokenDB = new TokenDB();
        Scanner scanner = new Scanner(tokenDB);

        readInputFile(inputFile, scanner);
        writeOutputFile(outputFile, scanner);
    }

    private static void readInputFile(File inputFile, Scanner scanner) throws FileNotFoundException {
        try(java.util.Scanner fileScanner = new java.util.Scanner(inputFile)){
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                for(char c : line.toCharArray()) {
                    scanner.nextChar(c);
                }
                scanner.nextChar('\n');
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + inputFile.getAbsolutePath());
        }
    }

    private static void writeOutputFile(File outputFile, Scanner scanner) throws IOException {
        if(outputFile.exists()){
            outputFile.delete();
        }
        outputFile.createNewFile();
        try(FileWriter fileWriter = new FileWriter(outputFile)){
            fileWriter.write("<html><head><title>Scanner output</title></head><body>");
            for(TokenDTO token : scanner.getResult()){
                    fileWriter.write(token.toHTML());
                }
            fileWriter.write("</body></html>");
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + outputFile.getAbsolutePath());
        }
    }


}