# File Handler
File handler é uma biblioteca para manipulação básica de arquivos na linguagem Java.

## Features
Possui os segguintes recurosos:
* Criação de arquivos juntamente com toda a sua estrutura de diretórios (se não existirem);
* Escrita em arquivos;
* Leitura de arquivos;
* Append (Junção) do novo texto com o que o arquivo já possuía;

### Usando
Pode escrever em um arquivo usando uma String (será pulado linha para cada \n):
```Java
FileHandler.escreverArquivo("dados/meuarquivo.txt", "Meu texto");
```

Ou você pode usar uma Lista de String (pulando linha para cada elemento):
```Java
List<String> linhas = new ArrayList<>(3);
linhas.add("Águia");
linhas.add("Galvão");
linhas.add("Harpia");
FileHandler.escreverArquivo("animais/aves.txt", linhas);
```

Não se limita apenas a arquivos .txt, pode ser xml, html, ou qualquer outro tipo de arquivo que usa caracteres:
```Java
String texto = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<Alimentos>\n"
                + "\t<Item>Arroz</Item>\n"
                + "\t<Item>Cenoura</Item>\n"
                + "</Alimentos>\n"
                + "<Limpeza>\n"
                + "\t<Item>Sabão em pó</Item>\n"
                + "</Limpeza>";
FileHandler.escreverArquivo(new File("Lista Compras.xml"), texto);
```

Para ler o arquivo basta fornecer um File (java.io.File) ou apenas o seu nome:
```Java
List<String> linhas = FileHandler.lerArquivo("ListaCompras.xml");
```

Para usar o Append (adicionar novas linhas ao aruivo sem remover o que ele tinha):
```Java
List<String> linhas = FileHandler.lerArquivo("Lista Compras.xml");
```