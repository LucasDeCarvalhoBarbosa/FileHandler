package file.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas Barbosa
 */
public class FileHandler {
    
    /**
     * Escreve o conteúdo de cada String da lista no arquivo informado,<br>
     * pulando linha para cada String da lista.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param nomeArquivo o nome do arquivo para escrever (ex: historia.txt)
     * @param linhas a lista com as Strings que devem ser escritas no arquivo
     * @return o Arquivo que será escrito
     * @throws IOException se não for possível escrever no arquivo
     */
    public static File escreverArquivo(String nomeArquivo, List<String> linhas) throws IOException {
        return escreverArquivo(new File(nomeArquivo), linhas);
    }
    
    /**
     * Escreve o conteúdo da String da lista no arquivo informado,<br>
     * pulando linha para cada \n.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param arquivo o arquivo {@link java.io.File} para escrever
     * @param texto o texto que vai ser escrito no arquivo
     * @return o Arquivo que será escrito
     * @throws IOException se não for possível escrever no arquivo
     */
    public static File escreverArquivo(File arquivo, String texto) throws IOException {
        List<String> linhas = new ArrayList<>();
        String[] array = texto.split("\n");
        for (int i = 0; i < array.length; i++) {
            linhas.add(array[i]);
        }
        
        return escreverArquivo(arquivo, linhas);
    }
    
    /**
     * Escreve o conteúdo da String da lista no arquivo informado,<br>
     * pulando linha para cada \n.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param nomeArquivo o nome do arquivo para escrever
     * @param texto o texto que vai ser escrito no arquivo
     * @return o Arquivo que será escrito
     * @throws IOException se não for possível escrever no arquivo
     */
    public static File escreverArquivo(String nomeArquivo, String texto) throws IOException {
        return escreverArquivo(new File(nomeArquivo), texto);
    }
    
    /**
     * Escreve o conteúdo de cada String da lista no arquivo informado,<br>
     * pulando linha para cada String da lista.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param arquivo o arquivo {@link java.io.File} para escrever
     * @param linhas a lista com as Strings que devem ser escritas no arquivo
     * @return o Arquivo que será escrito
     * @throws IOException se não for possível escrever no arquivo
     */
    public static File escreverArquivo(File arquivo, List<String> linhas) throws IOException {
        if(!arquivo.exists())
            criarArquivo(arquivo);
        
        FileWriter fileWriter = new FileWriter(arquivo);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for (int i = 0; i < linhas.size(); i++) {
            printWriter.println(linhas.get(i));
        }
        printWriter.close();
        fileWriter.close();
        
        return arquivo;
    }
    
    /**
     * Lê o conteúdo do arquivo informado e o retorna em forma de uma Lista de
     * Strings.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param nomeArquivo o nome do arquivo que será lido (ex: historia.txt)
     * @return uma Lista de Strings com o conteúdo do arquivo
     * @throws FileNotFoundException se não for possível encontrar o arquivo
     */
    public static List<String> lerArquivo(String nomeArquivo) throws FileNotFoundException, IOException {
        return lerArquivo(new File(nomeArquivo));
    }
    
    /**
     * Lê o conteúdo do arquivo informado e o retorna em forma de uma Lista de
     * Strings.<br>
     * 
     * @param arquivo o arquivo {@link java.io.File} que será lido
     * @return uma Lista de Strings com o conteúdo do arquivo
     * @throws FileNotFoundException se não for possível encontrar o arquivo
     */
    public static List<String> lerArquivo(File arquivo) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(arquivo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        List<String> linhas = new ArrayList<>();
        String linhaAtual = bufferedReader.readLine();//lê a primeira linha
        while(linhaAtual != null){
            linhas.add(linhaAtual);
            linhaAtual = bufferedReader.readLine();
        }
        
        bufferedReader.close();
        fileReader.close();
        
        return linhas;
    }
    
    /**
     * Verifica se o arquivo existe, se não existir,
     * cria o arquivo vazio com todos os diretórios em sua estrutura.<br>
     * <b>Obs: Separadores de diretório suportados:</b>
     * <ul>
     * <li> / ( barra simples)</li>
     * <li> // (barra dupla)</li>
     * <li> \  (barra invertida)</li>
     * <li> \\ (barra invertida dupla)</li>
     * </ul>
     * 
     * @param nomeArquivo o nome do arquivo que será criado
     * @return true se o arquivo foi criado com sucesso ou já existia.
     * @throws IOException se não for possível criar o arquivo
     */
    public static boolean criarArquivo(String nomeArquivo) throws IOException {
        return criarArquivo(new File(nomeArquivo));
    }
    
    /**
     * Verifica se o arquivo existe, se não existir,
     * cria o arquivo vazio com todos os diretórios em sua estrutura.
     * 
     * @param arquivo o arquivo que será criado
     * @return true se o arquivo foi criado com sucesso ou já existia.
     * @throws IOException se não for possível criar o arquivo
     */
    public static boolean criarArquivo(File arquivo) throws IOException {
        String nome = arquivo.getPath();
        
        if(!arquivo.exists()){
            
            //Unifico os possíveis separadores de diretórios
            String separador = "/";
            String aux = nome.replaceAll("//|\\\\|\\\\\\\\", separador);
            
            //Crio os diretórios na estrutura em que está o arquivo
            if(aux.contains(separador)){
                String diretorios = nome.substring(0, aux.lastIndexOf(separador));
                new File(diretorios).mkdirs();
            }
            
            return arquivo.createNewFile();
        }
        
        return true;
    }
    
    /**
     * Junta o conteúdo de cada String da lista no arquivo informado,<br>
     * pulando linha para cada String da lista.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param arquivo o arquivo para juntar
     * @param linhas a lista com as Strings que devem ser juntadas no arquivo
     * @return o Arquivo que será escrito
     * @throws IOException se não for possível escrever no arquivo
     */
    public static File appendArquivo(File arquivo, List<String> linhas) throws IOException {
        if(!arquivo.exists())
            criarArquivo(arquivo);
        
        FileWriter fileWriter = new FileWriter(arquivo);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        
        for (int i = 0; i < linhas.size(); i++) {
            printWriter.append(linhas.get(i));
        }
        printWriter.close();
        fileWriter.close();
        
        return arquivo;
    }
    
    /**
     * Junta o conteúdo de cada String da lista no arquivo informado,<br>
     * pulando linha para cada String da lista.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param nomeArquivo o nome do arquivo para juntar
     * @param linhas a lista com as Strings que devem ser juntadas no arquivo
     * @return o Arquivo que será escrito
     * @throws IOException se não for possível escrever no arquivo
     */
    public static File appendArquivo(String nomeArquivo, List<String> linhas) throws IOException {
        return appendArquivo(new File(nomeArquivo), linhas);
    }
    
    /**
     * Junta o conteúdo da String da lista no arquivo informado,<br>
     * pulando linha para cada \n.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param arquivo o arquivo {@link java.io.File} para juntar
     * @param texto o texto que vai ser escrito no arquivo
     * @return o Arquivo que será escrito
     * @throws IOException se não for possível escrever no arquivo
     */
    public static File appendArquivo(File arquivo, String texto) throws IOException {
        List<String> linhas = new ArrayList<>();
        String[] array = texto.split("\n");
        for (int i = 0; i < array.length; i++) {
            linhas.add(array[i]);
        }
        
        return appendArquivo(arquivo, linhas);
    }
    
    /**
     * Junta o conteúdo da String da lista no arquivo informado,<br>
     * pulando linha para cada \n.<br>
     * <b>Obs: Se o arquivo não for encontrado, será criado um.</b>
     * 
     * @param nomeArquivo o nome do arquivo {@link java.io.File} para juntar
     * @param texto o texto que vai ser escrito no arquivo
     * @return o Arquivo que será escrito
     * @throws IOException se não for possível escrever no arquivo
     */
    public static File appendArquivo(String nomeArquivo, String texto) throws IOException {
        return appendArquivo(new File(nomeArquivo), texto);
    }
    
}
