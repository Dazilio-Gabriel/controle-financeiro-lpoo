# 🎤 Guia de apresentação: Controle Financeiro

> Trabalho final de LPOO (FAESA 2026/2). Este guia explica o código em linguagem simples, para você saber o que é cada coisa e conseguir explicar na apresentação.
>
> Cada conceito tem três partes: **o que é** (sem termo técnico), **onde está no nosso código** e uma **fala pronta** (🗣️) para usar.

## Sumário

- [Quem apresenta o quê](#quem-apresenta-o-quê)

1. [O programa em 30 segundos](#1-o-programa-em-30-segundos)
2. [Mapa das classes](#2-mapa-das-classes)
3. [Conceitos de POO, um por um](#3-conceitos-de-poo-um-por-um)
4. [Tratamento de exceção](#4-tratamento-de-exceção)
5. [Coleções](#5-coleções)
6. [Leitura e gravação em arquivo](#6-leitura-e-gravação-em-arquivo)
7. [A tela em JavaFX e os botões](#7-a-tela-em-javafx-e-os-botões)
8. [O caminho completo de um clique](#8-o-caminho-completo-de-um-clique)
9. [Roteiro de demonstração](#9-roteiro-de-demonstração)
10. [Perguntas que a professora pode fazer](#10-perguntas-que-a-professora-pode-fazer)
11. [Cola rápida](#11-cola-rápida)

---

## Quem apresenta o quê

A apresentação está dividida em três partes, nesta ordem. Ela vai do mais concreto (o que o programa faz) para o mais abstrato (polimorfismo e a tela), e cada um cobre pelo menos um dos seis conceitos do enunciado.

| Ordem | Quem | Tempo | Tema | Conceitos do enunciado | Estudar | Demonstração |
|---|---|---|---|---|---|---|
| 1º | **Belfor** | ~3 min | O que o programa faz e como os dados são organizados | Herança | Seções 1, 2, 3.1, 3.2 e 3.3 | Passos 1 e 2 |
| 2º | **Gabriel** | ~4 min | Erros, listas e arquivo | Exceção, coleções, leitura e gravação | Seções 4, 5 e 6 | Passos 3 a 7 |
| 3º | **Rodrigo** | ~5 min | Polimorfismo e a tela | Polimorfismo e interface gráfica | Seções 3.4, 3.5, 3.6, 7 e 8 | Passos 8 a 10 |

Os passos da demonstração estão na [seção 9](#9-roteiro-de-demonstração) e as perguntas numeradas na [seção 10](#10-perguntas-que-a-professora-pode-fazer).

**Para os três:**

- A nota é individual e a professora pode perguntar qualquer coisa para qualquer um. Além da sua parte, leia a seção 2 (mapa das classes) e a seção 11 (cola rápida).
- Quem não falar na apresentação fica com zero no trabalho, então todo mundo precisa falar a sua parte.
- Combinem quem mexe no computador. O mais simples é uma pessoa só operar o programa do começo ao fim, enquanto quem está falando diz o que clicar.
- As falas prontas (🗣️) não são para decorar. Leia em voz alta duas ou três vezes e depois fale com as suas palavras.
- A seção 3.7 (`static` e `final`) não entra na fala de ninguém. Se ela perguntar, a resposta está na cola rápida.

### Parte 1: Belfor (o programa e os dados)

O que falar, na ordem:

1. **Abertura:** a fala pronta da seção 1.
2. **Demonstração, passos 1 e 2:** abrir o programa e lançar a "Conta de luz".
3. **Mapa das classes:** o sistema tem três partes, que são a tela, as regras e o arquivo (seção 2).
4. **Classe e objeto:** cada linha da tabela é um objeto (seção 3.1).
5. **Encapsulamento:** abrir `Lancamento.java` e mostrar os quatro atributos `private`, que só têm `get` (seção 3.2).
6. **Herança:** mostrar `Receita extends Lancamento`, `DespesaParcelada extends Despesa` e o `super` (seção 3.3).

Arquivos para deixar abertos: `Lancamento.java`, `Receita.java` e `DespesaParcelada.java`.

Perguntas que podem vir: 4 (o que o `super` faz), 5 (por que não tem `set`) e 15 (a parcelada desconta tudo de uma vez?).

> 🗣️ **Passagem:** "Agora o Gabriel mostra o que acontece quando o usuário digita um dado errado."

### Parte 2: Gabriel (erros, listas e arquivo)

O que falar, na ordem:

1. **Exceção, com os passos 3 e 4 da demonstração:** provocar os dois erros e depois mostrar `LancamentoInvalidoException.java`, um `throw` no construtor de `Lancamento` e o `try/catch` de `adicionarLancamento()` (seção 4).
2. **Coleções, com o passo 5:** mostrar a `ArrayList` em `ControleFinanceiro`, abrir o Resumo por categoria e explicar o `TreeMap` de `calcularDespesasPorCategoria()` (seção 5).
3. **Arquivo, com os passos 6 e 7:** abrir o `.csv`, fechar e abrir o programa, e mostrar `ler()` e `gravar()` em `ArquivoLancamentos.java`, incluindo o `finally` (seção 6).

Arquivos para deixar abertos: `LancamentoInvalidoException.java`, `ControleFinanceiro.java`, `ArquivoLancamentos.java` e `TelaPrincipal.java` (métodos `lerValor` e `adicionarLancamento`).

Perguntas que podem vir: 6 (`throw` e `throws`), 7 (por que uma exceção própria), 8 (`finally`), 9 (`ArrayList` ou vetor), 10 (por que `TreeMap`), 11 (arquivo que não existe ou com linha errada), 16 (`double` para dinheiro) e 18 (regravar o arquivo inteiro).

> 🗣️ **Passagem:** "Para fechar, o Rodrigo mostra como o saldo é calculado e como a tela funciona."

### Parte 3: Rodrigo (polimorfismo e a tela)

O que falar, na ordem:

1. **Classe abstrata:** por que `Lancamento` é `abstract` e o que são os métodos abstratos (seção 3.4).
2. **Polimorfismo, com os passos 8 e 9:** lançar a Geladeira parcelada e mostrar o `calcularSaldo()`, que não tem nenhum `if`, mais a tabela de respostas de cada classe (seção 3.5).
3. **Interface:** o `Comparable` explica por que a Geladeira entrou no meio da tabela, pela data (seção 3.6).
4. **Tela e botões, com o passo 10:** `Stage`, `Scene` e layouts; depois `setOnAction(this)`, `handle` e `getSource()` (seção 7).
5. **Fechamento:** o caminho completo de um clique, que junta as três partes (seção 8).

Arquivos para deixar abertos: `ControleFinanceiro.java` (método `calcularSaldo`), `Lancamento.java`, `TelaPrincipal.java` e `Main.java`.

Perguntas que podem vir: 1 (por que abstrata), 2 (onde está o polimorfismo), 3 (sobrescrita e sobrecarga), 12 (o `this` no `setOnAction`), 13 (`Main` separado), 14 (aviso no console) e 17 (interface criada pelo grupo).

---

## 1. O programa em 30 segundos

É um **controle de orçamento**. O usuário lança o que entrou (receitas) e o que saiu (despesas e despesas parceladas). O programa mostra tudo numa tabela, calcula o saldo, soma os gastos por categoria e guarda os dados num arquivo `.csv`, então nada se perde ao fechar.

O tema é uma parte do Jarvis Financeiro, o nosso Projeto Integrador, refeita em Java puro.

> 🗣️ **Abertura:** "Nosso trabalho é um controle financeiro. A gente lança receitas e despesas, o sistema calcula o saldo e o total por categoria, e grava tudo em um arquivo CSV. Ele usa os seis conceitos pedidos: herança, polimorfismo, exceção, coleções, leitura e gravação e interface gráfica em JavaFX."

Os seis conceitos do enunciado e onde cada um aparece:

| Pedido no enunciado | Onde está | Seção deste guia |
|---|---|---|
| Herança | `Lancamento` → `Receita`, `Despesa` → `DespesaParcelada` | 3.3 |
| Polimorfismo | `getValorNoSaldo()`, `getTipo()`, usados em `calcularSaldo()` | 3.5 |
| Tratamento de exceção | `LancamentoInvalidoException`, `try/catch/finally` | 4 |
| Coleções | `ArrayList<Lancamento>` e `TreeMap<String, Double>` | 5 |
| Leitura e gravação | `ArquivoLancamentos` com `dados/lancamentos.csv` | 6 |
| Interface gráfica | `TelaPrincipal` em JavaFX | 7 |

---

## 2. Mapa das classes

São 10 classes, todas no pacote `TrabFinal`. Pense numa pequena empresa em que cada funcionário tem uma função só:

| Classe | O que faz | Analogia |
|---|---|---|
| `Main` | Liga o programa. | A chave de ignição. |
| `TelaPrincipal` | Mostra a janela e reage aos cliques. | O atendente do balcão: conversa com o cliente, mas não faz conta. |
| `ControleFinanceiro` | Guarda a lista de lançamentos e faz as contas. | O contador. |
| `ArquivoLancamentos` | Lê e grava o arquivo `.csv`. | O arquivista. |
| `Lancamento` | Molde geral de qualquer lançamento (abstrata). | A ficha em branco. |
| `Receita` | Lançamento de dinheiro que entra. | Ficha de entrada. |
| `Despesa` | Lançamento de dinheiro que sai. | Ficha de saída. |
| `DespesaParcelada` | Despesa dividida em parcelas. | Ficha de saída com carnê. |
| `LancamentoInvalidoException` | O erro que o sistema dispara quando um dado está errado. | O alarme. |
| `Formatador` | Converte data e dinheiro entre texto e número. | O tradutor. |

Como elas se ligam:

```
                    Main
                      │ abre
                      ▼
                TelaPrincipal ───────────────► ArquivoLancamentos
                      │ usa                      (lê e grava o .csv)
                      ▼
              ControleFinanceiro
                      │ guarda uma lista de
                      ▼
                 Lancamento  (abstrata)
                 ┌────┴────┐
              Receita   Despesa
                           │
                    DespesaParcelada
```

> 🗣️ **Fala pronta:** "Separamos o sistema em três partes: a tela, as regras e o arquivo. A tela não faz conta e não mexe em arquivo; ela só pede para as outras classes. Assim, quando trocamos a tela de Swing para JavaFX, as outras classes quase não mudaram."

---

## 3. Conceitos de POO, um por um

### 3.1 Classe, objeto e construtor

**O que é.** A classe é o **molde**; o objeto é a **coisa feita com o molde**. A classe `Receita` é a forma de bolo; cada receita lançada ("Salário", "Freela de site") é um bolo. O **construtor** é o método que monta o objeto, e roda quando a gente escreve `new`.

**No nosso código.** Cada linha da tabela é um objeto:

```java
new Receita("Salário", 4200, data, "Salário");
new DespesaParcelada("Celular novo", 1800, data, "Outras despesas", 6);
```

> 🗣️ "Classe é o molde, objeto é o que foi criado a partir dele. Cada linha da tabela é um objeto de `Receita`, `Despesa` ou `DespesaParcelada`."

### 3.2 Encapsulamento

**O que é.** É **proteger os dados** do objeto. Os atributos ficam trancados (`private`) e só se chega neles pelos métodos que a classe oferece. É como um caixa eletrônico: você não abre o cofre, você usa os botões, e a máquina confere se a operação é válida.

**No nosso código.** Em `Lancamento` os quatro atributos são `private`:

```java
private String descricao;
private double valor;
private LocalDate data;
private String categoria;
```

Para ler existe o `get` (`getDescricao()`, `getValor()`...). **Não existe `set`**: depois de criado, o lançamento não muda. A única porta de entrada dos dados é o construtor, e ele confere tudo antes de aceitar:

```java
if (descricao == null || descricao.trim().isEmpty()) {
    throw new LancamentoInvalidoException("Informe a descrição do lançamento.");
}
```

Resultado: **não existe lançamento inválido dentro do sistema**. Valor zerado, descrição vazia ou data faltando são barrados na criação.

> 🗣️ "Encapsulamento é esconder os dados e controlar o acesso. Nossos atributos são `private`, só têm `get`, e toda validação fica no construtor. Então é impossível existir um lançamento com valor negativo ou sem descrição."

### 3.3 Herança

**O que é.** Uma classe **filha** recebe tudo o que a classe **mãe** tem e acrescenta o que é só dela. Evita repetir código. A palavra do Java é `extends`.

**No nosso código.** Todo lançamento tem descrição, valor, data e categoria. Isso foi escrito **uma vez só**, em `Lancamento`, e as filhas herdam:

```java
public class Receita extends Lancamento { ... }
public class Despesa extends Lancamento { ... }
public class DespesaParcelada extends Despesa { ... }
```

A `DespesaParcelada` é herança em **dois níveis**: ela é uma `Despesa`, que é um `Lancamento`. Ela só acrescenta o que tem de diferente, o atributo `parcelas`.

O `super(...)` chama o construtor da classe mãe, que guarda e valida os dados comuns:

```java
public DespesaParcelada(String descricao, double valor, LocalDate data, String categoria, int parcelas)
        throws LancamentoInvalidoException {
    super(descricao, valor, data, categoria);   // a mãe (Despesa) cuida dos dados comuns

    if (parcelas < 2) {
        throw new LancamentoInvalidoException("Uma despesa parcelada precisa ter pelo menos 2 parcelas.");
    }

    this.parcelas = parcelas;                   // a filha cuida só do que é dela
}
```

Também usamos herança de classes prontas do Java: `TelaPrincipal extends Application` (a janela do JavaFX) e `LancamentoInvalidoException extends Exception`.

> 🗣️ "Herança é reaproveitar código: a filha ganha tudo da mãe. `Receita` e `Despesa` herdam de `Lancamento`, e `DespesaParcelada` herda de `Despesa`. O `super` chama o construtor da mãe."

### 3.4 Classe abstrata e método abstrato

**O que é.** Classe abstrata é um **molde incompleto**: serve de base para outras, mas não dá para criar objeto direto dela. Método abstrato é um método **sem corpo**, que obriga cada filha a escrever a sua versão.

**No nosso código.** Não existe "lançamento genérico" no mundo real: ou é receita ou é despesa. Por isso:

```java
public abstract class Lancamento implements Comparable<Lancamento> {

    public abstract String getTipo();
    public abstract double getValorNoSaldo();
```

Escrever `new Lancamento(...)` dá erro de compilação. E toda filha é **obrigada** a ter `getTipo()` e `getValorNoSaldo()`.

> 🗣️ "`Lancamento` é abstrata porque não faz sentido existir um lançamento que não seja receita nem despesa. Os métodos abstratos obrigam cada filha a dizer qual é o seu tipo e como ela mexe no saldo."

### 3.5 Polimorfismo

**O que é.** "Muitas formas": **a mesma chamada, respostas diferentes**, conforme a classe do objeto. É como apertar "tocar" em vários instrumentos: o comando é o mesmo, o som muda.

**No nosso código.** Cada classe responde do seu jeito aos mesmos métodos:

| Método | `Receita` | `Despesa` | `DespesaParcelada` |
|---|---|---|---|
| `getTipo()` | "Receita" | "Despesa" | "Despesa parcelada" |
| `getValorNoSaldo()` | + valor | − valor | − valor (herdado de `Despesa`) |
| `getParcelas()` | 1 (herdado) | 1 (herdado) | o número de parcelas |
| `getObservacao()` | vazio (herdado) | vazio (herdado) | "6x de R$ 300,00" |

O melhor exemplo é o cálculo do saldo, em `ControleFinanceiro`:

```java
public double calcularSaldo() {
    double saldo = 0;

    for (Lancamento lancamento : lancamentos) {
        saldo = saldo + lancamento.getValorNoSaldo();
    }

    return Formatador.arredondar(saldo);
}
```

Repare que **não tem nenhum `if` perguntando "é receita ou despesa?"**. A lista é de `Lancamento`, e cada objeto sabe sozinho se soma ou desconta. A receita devolve positivo, a despesa devolve negativo.

O `@Override` em cima de um método avisa: "estou reescrevendo um método que veio da classe mãe". Isso se chama **sobrescrita**.

> 🗣️ "Polimorfismo é o mesmo método se comportar diferente em cada classe. No `calcularSaldo` a gente percorre uma lista de `Lancamento` e chama `getValorNoSaldo()`. A receita responde positivo e a despesa responde negativo, sem nenhum `if`. Se amanhã criarmos um tipo novo, esse método nem precisa mudar."

### 3.6 Interface

**Atenção: a palavra tem dois sentidos.** "Interface gráfica" é a tela. Já `interface` no Java é um **contrato**: uma lista de métodos que a classe promete ter. A palavra é `implements`.

**No nosso código** usamos duas interfaces prontas:

| Interface | Quem implementa | Método prometido | Para quê |
|---|---|---|---|
| `Comparable<Lancamento>` | `Lancamento` | `compareTo` | Dizer como comparar dois lançamentos (pela data), para a lista poder ser ordenada. |
| `EventHandler<ActionEvent>` | `TelaPrincipal` | `handle` | Dizer o que fazer quando um botão é clicado. |

```java
@Override
public int compareTo(Lancamento outro) {
    return data.compareTo(outro.data);
}
```

> 🗣️ "Interface é um contrato. `Lancamento` implementa `Comparable`, então promete ter o `compareTo`, e é isso que deixa o `Collections.sort` ordenar a lista por data."

### 3.7 `static` e `final`

**O que é.** `static` é o que **pertence à classe**, não a um objeto; usa-se sem `new`. `final` é o que **não muda mais** (constante).

**No nosso código:**

- `Receita.TIPO`, `Despesa.CATEGORIAS`: constantes (`static final`). O texto "Receita" fica escrito num lugar só.
- `Formatador.formatarMoeda(valor)`: método `static`. Não precisa criar um objeto `Formatador` para formatar um valor.
- `ControleFinanceiro.criarLancamento(...)`: método `static` que recebe o nome do tipo e devolve o objeto da classe certa. É usado pela tela **e** pela leitura do arquivo, para esse código não ficar repetido.

> 🗣️ "`static` é da classe, não do objeto, por isso chamamos `Formatador.formatarMoeda` sem dar `new`. As constantes são `static final` para o valor ficar em um lugar só."

---

## 4. Tratamento de exceção

**O que é.** Exceção é o jeito do Java de avisar que **algo deu errado** sem derrubar o programa. Funciona como um alarme: alguém **dispara** e alguém **atende**.

| Palavra | O que faz | Analogia |
|---|---|---|
| `throw` | Dispara o erro naquele ponto. | Puxar o alarme. |
| `throws` | Avisa, na assinatura do método, que ele pode disparar. | A placa "aqui pode tocar alarme". |
| `try` | Bloco onde o erro pode acontecer. | A área vigiada. |
| `catch` | O que fazer se o erro acontecer. | O bombeiro. |
| `finally` | Roda sempre, com erro ou sem. | Trancar a porta ao sair, aconteça o que acontecer. |

### Nossa própria exceção

```java
public class LancamentoInvalidoException extends Exception {

    public LancamentoInvalidoException(String mensagem) {
        super(mensagem);
    }

}
```

Ela herda de `Exception`, então é do tipo **verificada**: o Java obriga quem chama a tratar com `try/catch`. Não dá para esquecer.

### Onde o alarme é disparado (`throw`)

| Situação | Onde |
|---|---|
| Descrição vazia, valor zero ou negativo, data ou categoria faltando | construtor de `Lancamento` |
| Parcelada com menos de 2 parcelas | construtor de `DespesaParcelada` |
| Data que não existe (31/02) ou fora do formato | `Formatador.converterData` |
| Valor ou parcelas com letras | `TelaPrincipal.lerValor` e `lerParcelas` |
| Tipo desconhecido | `ControleFinanceiro.criarLancamento` |
| Linha do arquivo com defeito | `ArquivoLancamentos.converterLinha` |

### Onde o alarme é atendido (`try/catch`)

Na tela, em `adicionarLancamento()`:

```java
try {
    // lê os campos, cria o lançamento, adiciona, salva...
} catch (LancamentoInvalidoException e) {
    mostrarErro(e.getMessage());
}
```

Se qualquer dado estiver errado, o código pula direto para o `catch`: o lançamento **não é criado** e aparece uma janela com a mensagem. O programa continua aberto.

### Trocando o erro do Java pelo nosso

Quando o usuário digita "abc" no valor, o Java dispara `NumberFormatException`, com uma mensagem técnica em inglês. A gente **pega esse erro e dispara o nosso**, com mensagem clara:

```java
private double lerValor() throws LancamentoInvalidoException {
    try {
        return Double.parseDouble(campoValor.getText().trim().replace(",", "."));
    } catch (NumberFormatException e) {
        throw new LancamentoInvalidoException("Valor inválido. Digite só números, por exemplo: 150,90");
    }
}
```

O mesmo acontece com a data: `DateTimeParseException` vira `LancamentoInvalidoException`.

### O `finally` no arquivo

Em `ArquivoLancamentos`, o arquivo é fechado no `finally`, então ele fecha **mesmo se der erro no meio da leitura**:

```java
} finally {
    if (leitor != null) {
        leitor.close();
    }
}
```

Erros de disco (`IOException`) são tratados na tela, em `carregarLancamentos()` e `salvarLancamentos()`.

> 🗣️ "Criamos a `LancamentoInvalidoException`. As classes disparam com `throw` quando o dado está errado, e a tela atende com `try/catch`, mostrando a mensagem numa janela. O programa nunca fecha por causa de dado errado. No arquivo usamos `finally` para garantir que ele seja fechado."

---

## 5. Coleções

**O que é.** São as "caixas" do Java para guardar vários objetos. Usamos duas.

### `ArrayList<Lancamento>`: a lista

Uma lista que **cresce sozinha**. Um vetor comum tem tamanho fixo; a `ArrayList` não, e isso é necessário porque não sabemos quantos lançamentos o usuário vai fazer.

Fica em `ControleFinanceiro`:

```java
private ArrayList<Lancamento> lancamentos;
```

O `<Lancamento>` diz que a lista só aceita lançamentos. Como `Receita` e `Despesa` **são** lançamentos, entram todos na mesma lista. É isso que permite o polimorfismo.

| Operação | Código |
|---|---|
| Adicionar | `lancamentos.add(lancamento)` |
| Remover | `lancamentos.remove(lancamento)` |
| Percorrer | `for (Lancamento lancamento : lancamentos)` |
| Ordenar por data | `Collections.sort(lancamentos)` |

O `Collections.sort` usa o `compareTo` de `Lancamento` (seção 3.6). Por isso um lançamento novo com data antiga aparece no lugar certo da tabela, e não no fim.

### `TreeMap<String, Double>`: o dicionário

Guarda **pares chave → valor**. No nosso caso: categoria → total gasto. E já mantém as chaves em **ordem alfabética**.

Usado em `calcularDespesasPorCategoria()`:

```java
TreeMap<String, Double> totais = new TreeMap<String, Double>();

for (Lancamento lancamento : lancamentos) {
    if (lancamento.getValorNoSaldo() < 0) {              // só despesas
        String categoria = lancamento.getCategoria();
        double acumulado = 0;

        if (totais.containsKey(categoria)) {             // já tem essa categoria?
            acumulado = totais.get(categoria);           // pega o que já somou
        }

        totais.put(categoria, Formatador.arredondar(acumulado - lancamento.getValorNoSaldo()));
    }
}
```

Em português: para cada despesa, olha a categoria, pega quanto já tinha somado nela e guarda o novo total.

> 🗣️ "Usamos `ArrayList` para guardar os lançamentos, porque ela cresce conforme o usuário adiciona, e `TreeMap` para somar por categoria, porque ele guarda pares de categoria e total e já deixa em ordem alfabética."

---

## 6. Leitura e gravação em arquivo

**O que é.** Guardar os dados no disco para eles continuarem lá quando o programa fechar.

O arquivo é `dados/lancamentos.csv`. CSV é um arquivo de texto comum, com **uma linha por lançamento** e os campos separados por ponto e vírgula:

```
tipo;data;descricao;categoria;valor;parcelas
Receita;05/11/2026;Salário;Salário;4200.0;1
Despesa parcelada;10/11/2026;Celular novo;Outras despesas;1800.0;6
```

Tudo fica na classe `ArquivoLancamentos`:

| Método | Quando roda | O que faz |
|---|---|---|
| `ler()` | Uma vez, quando o programa abre. | Lê linha por linha e transforma cada uma em objeto. |
| `gravar(lista)` | Depois de cada adição ou remoção. | Regrava o arquivo inteiro com a lista atual. |

**Leitura, passo a passo:**

1. Se o arquivo não existe (primeira vez), devolve uma lista vazia.
2. `BufferedReader` lê uma linha de cada vez. A primeira é o cabeçalho e é descartada.
3. `linha.split(";")` quebra a linha em 6 pedaços.
4. Cada pedaço é convertido (texto → data, texto → número).
5. `ControleFinanceiro.criarLancamento(...)` cria o objeto da classe certa conforme o campo `tipo`.
6. Se a linha estiver estragada, ela é **pulada** e contada. As outras continuam sendo lidas, e a tela avisa quantas ficaram de fora.

**Gravação:** `BufferedWriter` escreve o cabeçalho e depois uma linha por lançamento. Para montar a linha, o código chama `getTipo()` e `getParcelas()`, que respondem certo para cada classe. É polimorfismo de novo: o arquivo não precisa saber qual é a subclasse.

> 🗣️ "Os dados ficam num arquivo CSV. A classe `ArquivoLancamentos` lê o arquivo quando o programa abre e regrava a cada mudança. Se uma linha estiver com erro, ela é ignorada e o resto é carregado normalmente."

---

## 7. A tela em JavaFX e os botões

### 7.1 As peças do JavaFX

| Peça | O que é | Analogia |
|---|---|---|
| `Stage` | A janela. | O palco do teatro. |
| `Scene` | O conteúdo dentro da janela. | A cena montada no palco. |
| Layouts (`VBox`, `HBox`, `GridPane`) | Organizam a posição das coisas. | As prateleiras. |
| Controles (`Button`, `TextField`, `ComboBox`, `TableView`, `Label`) | O que o usuário vê e usa. | Os objetos nas prateleiras. |

### 7.2 Como o programa abre

```java
public class Main {
    public static void main(String[] args) {
        Application.launch(TelaPrincipal.class, args);
    }
}
```

O `launch` liga o JavaFX, cria a `TelaPrincipal` e chama o método `start`. O `start` faz quatro coisas: monta a tela, mostra a janela, lê o arquivo e preenche a tabela.

### 7.3 Como a tela é montada

```
┌─ Novo lançamento ─────────────────────────────────────────────────┐
│ Tipo ▼ │ Descrição │ Valor (R$) │ Data │ Categoria ▼ │ Parcelas   │ ← GridPane (6 colunas)
│ [Adicionar] [Remover selecionado] [Resumo por categoria]          │ ← HBox (lado a lado)
├─ Lançamentos ─────────────────────────────────────────────────────┤
│ Data │ Tipo │ Descrição │ Categoria │ Valor │ Observação          │ ← TableView
├─ Resumo ──────────────────────────────────────────────────────────┤
│ Receitas: R$ ...      Despesas: R$ ...      Saldo: R$ ...         │ ← HBox com 3 Label
└───────────────────────────────────────────────────────────────────┘
        os três painéis ficam empilhados dentro de uma VBox
```

| Layout | Como organiza |
|---|---|
| `VBox` | Um embaixo do outro (vertical). |
| `HBox` | Um ao lado do outro (horizontal). |
| `GridPane` | Em grade, com linhas e colunas. |
| `TitledPane` | Uma moldura com título em cima. |

Cada pedaço da tela é montado por um método próprio (`criarPainelFormulario`, `criarPainelTabela`, `criarPainelResumo`, `criarBotao`, `criarColuna`), para não repetir código.

### 7.4 Como os botões funcionam

São três passos.

**Passo 1: criar o botão e dizer quem cuida do clique.**

```java
private Button criarBotao(String texto) {
    Button botao = new Button(texto);
    botao.setOnAction(this);
    return botao;
}
```

`setOnAction(this)` significa: "quando clicarem, avise **esta própria tela**". O `this` é a `TelaPrincipal`.

**Passo 2: a tela promete saber atender cliques.** Por isso ela implementa a interface `EventHandler<ActionEvent>`, que exige o método `handle`:

```java
public class TelaPrincipal extends Application implements EventHandler<ActionEvent> {
```

**Passo 3: o `handle` descobre quem foi clicado e chama o método certo.**

```java
@Override
public void handle(ActionEvent evento) {
    Object origem = evento.getSource();

    if (origem == botaoAdicionar) {
        adicionarLancamento();
    } else if (origem == botaoRemover) {
        removerLancamento();
    } else if (origem == botaoResumo) {
        mostrarResumoPorCategoria();
    } else if (origem == comboTipo) {
        atualizarCamposDoTipo();
    }
}
```

`evento.getSource()` devolve **qual componente gerou o evento**. Todo clique do programa passa por esse método, que funciona como uma central telefônica: recebe a ligação e transfere para o setor certo.

> 🗣️ "Cada botão é ligado à tela com `setOnAction(this)`. Quando o usuário clica, o JavaFX chama o método `handle`. Lá a gente usa `getSource()` para saber qual botão foi e chama o método correspondente."

### 7.5 O que cada botão faz

**Adicionar** (`adicionarLancamento`)

1. Lê o tipo e a categoria das caixas de seleção.
2. Converte o valor, a data e as parcelas (texto → número ou data).
3. Cria o objeto com `ControleFinanceiro.criarLancamento(...)`.
4. Adiciona na lista com `controle.adicionar(novo)`, que já ordena por data.
5. Grava o arquivo, atualiza a tabela e o resumo, e limpa os campos.
6. Se algo estiver errado em qualquer passo, cai no `catch` e mostra a janela de erro.

Apertar **Enter** em qualquer campo também adiciona, por causa de `botaoAdicionar.setDefaultButton(true)`.

**Remover selecionado** (`removerLancamento`)

1. Pega a linha selecionada: `tabela.getSelectionModel().getSelectedItem()`.
2. Se nada está selecionado, mostra um erro e para.
3. Abre uma janela de confirmação com Sim e Não.
4. Se a resposta for Sim, remove da lista, grava o arquivo e atualiza a tela.

**Resumo por categoria** (`mostrarResumoPorCategoria`)

1. Pede o `TreeMap` para `controle.calcularDespesasPorCategoria()`.
2. Monta um texto com uma linha por categoria e o total no fim.
3. Mostra numa janela de informação.

**Trocar o Tipo** (não é botão, mas gera evento igual; `atualizarCamposDoTipo`)

- Se for Receita, a lista de categorias vira a de receitas (Salário, Renda extra...). Senão, a de despesas.
- O campo Parcelas só fica liberado para Despesa parcelada. Nos outros tipos ele trava em 1.

### 7.6 Como a tabela se preenche

A tabela é uma `TableView<Lancamento>`: ela recebe a lista de objetos direto.

```java
tabela.getItems().setAll(controle.getLancamentos());
```

Cada coluna sabe qual `get` chamar pelo nome:

```java
criarColuna("Descrição", "descricao", 230)
```

O `PropertyValueFactory("descricao")` faz a coluna chamar `getDescricao()` de cada lançamento. A regra é: coloca `get` na frente e a primeira letra maiúscula.

| Coluna | Nome passado | Método chamado |
|---|---|---|
| Data | `dataFormatada` | `getDataFormatada()` |
| Tipo | `tipo` | `getTipo()` |
| Descrição | `descricao` | `getDescricao()` |
| Categoria | `categoria` | `getCategoria()` |
| Valor | `valorFormatado` | `getValorFormatado()` |
| Observação | `observacao` | `getObservacao()` |

Aqui também tem polimorfismo: a coluna Tipo chama `getTipo()` e cada objeto responde conforme a sua classe.

O método `atualizarTela()` redesenha a tabela e o resumo depois de cada mudança. O saldo fica **verde** quando é positivo e **vermelho** quando é negativo.

### 7.7 As janelas de aviso

São objetos `Alert`. Todas são montadas pelo mesmo método, `criarAlerta(tipo, titulo, mensagem)`:

| Tipo | Quando aparece |
|---|---|
| `ERROR` | Dado errado ou falha ao ler ou gravar o arquivo. |
| `CONFIRMATION` | Antes de remover. |
| `INFORMATION` | Resumo por categoria. |
| `WARNING` | Linhas do arquivo ignoradas na abertura. |

`showAndWait()` mostra a janela e **espera** o usuário responder antes de continuar.

---

## 8. O caminho completo de um clique

O que acontece, do começo ao fim, quando alguém clica em **Adicionar**:

```
Usuário clica em [Adicionar]
        │
        ▼
TelaPrincipal.handle(evento)          descobre qual botão foi clicado
        │
        ▼
adicionarLancamento()
        │
        ├─ 1. lê e converte os campos        lerValor, lerParcelas, Formatador.converterData
        │
        ├─ 2. cria o objeto                  ControleFinanceiro.criarLancamento
        │        │                           (vira Receita, Despesa ou DespesaParcelada;
        │        │                            o construtor valida os dados)
        │        │
        │        └─ dado errado? ──► throw LancamentoInvalidoException
        │                                    │
        │                                    ▼
        │                            catch ──► janela de erro, nada é gravado
        │
        ├─ 3. controle.adicionar(novo)       entra na ArrayList e a lista é ordenada por data
        │
        ├─ 4. salvarLancamentos()            ArquivoLancamentos.gravar regrava o .csv
        │
        ├─ 5. atualizarTela()                tabela e resumo são redesenhados
        │
        └─ 6. limparCampos()                 pronto para o próximo lançamento
```

> 🗣️ "O clique chega no `handle`, que chama `adicionarLancamento`. Ele lê os campos, cria o objeto, guarda na lista, grava no arquivo e redesenha a tela. Se o dado estiver errado, a exceção interrompe tudo antes de gravar."

---

## 9. Roteiro de demonstração

A demonstração acompanha a ordem das falas: cada um mostra na tela o que está explicando. Os valores abaixo consideram o arquivo de exemplo original e os passos feitos nesta ordem.

| # | Quem | O que fazer | O que mostrar e dizer |
|---|---|---|---|
| 1 | Belfor | Abrir o programa (`Main.java` → *Run As → Java Application*). | A tabela já vem com 8 lançamentos, lidos do arquivo. Saldo: R$ 863,70. |
| 2 | Belfor | Adicionar **Despesa**, "Conta de luz", valor `185,40`, categoria Moradia. | **Objeto novo:** apareceu uma linha na tabela. Despesas vão para R$ 4.321,70 e o saldo para R$ 678,30. |
| 3 | Gabriel | Escrever qualquer descrição, digitar `abc` no valor e clicar em Adicionar. | **Exceção:** aparece "Valor inválido..." e o programa continua aberto. Nada foi gravado. |
| 4 | Gabriel | Trocar o valor para `10` e a data para `31/02/2026`. Adicionar. | **Exceção:** "Data inválida...". O sistema recusa data que não existe. |
| 5 | Gabriel | Clicar em **Resumo por categoria**. | **TreeMap:** categorias em ordem alfabética. Moradia aparece com R$ 1.535,40 e o total é R$ 4.321,70. |
| 6 | Gabriel | Abrir `dados/lancamentos.csv`. | **Gravação:** a última linha é a Conta de luz. |
| 7 | Gabriel | Fechar e abrir o programa de novo. | **Leitura:** os 9 lançamentos continuam lá. |
| 8 | Rodrigo | Trocar o Tipo para **Despesa parcelada**. | **Eventos:** as categorias mudam e o campo Parcelas libera sozinho. |
| 9 | Rodrigo | Adicionar "Geladeira", valor `2400,50`, data `09/11/2026`, categoria Moradia, 10 parcelas. | **Polimorfismo:** a coluna Observação mostra "10x de R$ 240,05", que só a parcelada tem. A linha entra no meio da tabela, pela data (**ordenação**). O saldo fica **vermelho**: R$ -1.722,20. |
| 10 | Rodrigo | Selecionar a Geladeira e clicar em **Remover selecionado** → Sim. | **Botões e janelas:** aparece a confirmação e o saldo volta para R$ 678,30, verde. |

**Para ensaiar e depois voltar o arquivo ao original**, rode no terminal dentro da pasta do projeto:

```
git restore dados/lancamentos.csv
```

Dicas para a hora:

- Faça **um erro de cada vez** nos passos 3 e 4. Se o valor e a data estiverem errados juntos, só a mensagem do valor aparece, porque ele é conferido primeiro.
- No passo 6, se for abrir o `.csv` pelo Eclipse, clique no projeto e aperte **F5** antes, para ele recarregar o arquivo. Ou abra pelo Bloco de Notas.
- O aviso `Unsupported JavaFX configuration` no console é normal (veja a pergunta 14).

---

## 10. Perguntas que a professora pode fazer

**1. Por que `Lancamento` é abstrata?**
Porque não existe lançamento que não seja receita nem despesa. Ela só serve de base, e ninguém pode dar `new Lancamento`.

**2. Onde está o polimorfismo?**
No `calcularSaldo()` de `ControleFinanceiro`: percorre uma lista de `Lancamento` chamando `getValorNoSaldo()`, e cada objeto responde conforme a sua classe. Também na tabela (colunas Tipo, Valor e Observação) e na gravação do arquivo.

**3. Qual a diferença entre sobrescrita e sobrecarga? Vocês usaram as duas?**
Sobrescrita (`@Override`) é a filha reescrever um método da mãe; usamos bastante. Sobrecarga é ter dois métodos com o mesmo nome e parâmetros diferentes na mesma classe; **não usamos**.

**4. O que o `super` faz?**
Chama o construtor da classe mãe. A mãe guarda e valida os dados comuns, e a filha cuida só do que é dela.

**5. Por que não tem `set`?**
Para o lançamento não mudar depois de criado. Toda entrada de dado passa pelo construtor, que valida. Se precisar corrigir, remove e lança de novo.

**6. Qual a diferença entre `throw` e `throws`?**
`throw` dispara o erro naquele momento. `throws` fica na assinatura do método e avisa que ele pode disparar aquele erro.

**7. Por que criar uma exceção própria?**
Para ter um tipo de erro só do nosso sistema, com mensagens claras para o usuário, e separar "dado inválido" de outros erros, como falha de disco (`IOException`).

**8. Para que serve o `finally`?**
Para fechar o arquivo sempre, dando certo ou dando erro na leitura ou na gravação.

**9. Por que `ArrayList` e não vetor?**
Porque não sabemos quantos lançamentos vão existir. O vetor tem tamanho fixo e a `ArrayList` cresce sozinha.

**10. Por que `TreeMap`?**
Porque precisamos de pares categoria → total, e o `TreeMap` já devolve as categorias em ordem alfabética. Um `HashMap` faria a mesma soma, mas sem ordem garantida.

**11. O que acontece se o arquivo não existir ou tiver uma linha errada?**
Se não existir, o programa abre vazio e cria o arquivo no primeiro lançamento. Se uma linha estiver errada, ela é ignorada, as outras são lidas e a tela avisa quantas ficaram de fora.

**12. O que é o `this` no `setOnAction(this)`?**
É o próprio objeto da tela. Estamos dizendo ao botão que quem atende o clique é a `TelaPrincipal`, pelo método `handle`.

**13. Por que o `Main` é separado da `TelaPrincipal`?**
O `Main` só liga o programa e a `TelaPrincipal` cuida da janela. Além disso, com o `main` fora da classe que herda de `Application`, o programa roda só com os `.jar` do JavaFX no classpath, sem configuração extra.

**14. Que aviso é esse no console ao abrir?**
`Unsupported JavaFX configuration` só informa que o JavaFX foi carregado pelo classpath (os `.jar` da pasta `lib`) em vez de como módulo. Não é erro e o programa funciona normalmente.

**15. A despesa parcelada desconta tudo de uma vez?**
Sim. Para manter o trabalho simples, o saldo desconta o valor total da compra, e as parcelas aparecem na observação. Dividir por mês seria uma evolução.

**16. Por que `double` para dinheiro?**
Por simplicidade. Para evitar erro de arredondamento, toda soma passa por `Formatador.arredondar`, que deixa com 2 casas. Num sistema real o indicado seria `BigDecimal`.

**17. Vocês criaram alguma interface?**
Não criamos uma nova; implementamos duas prontas: `Comparable`, para ordenar por data, e `EventHandler`, para os cliques.

**18. Por que regravar o arquivo inteiro a cada mudança?**
Porque é simples e seguro para poucos dados: o arquivo sempre fica igual à lista da memória.

---

## 11. Cola rápida

| Termo | Em uma frase | Onde ver |
|---|---|---|
| Classe | O molde. | Qualquer arquivo `.java` |
| Objeto | O que foi criado com o molde (`new`). | Cada linha da tabela |
| Construtor | Método que monta o objeto. | `Lancamento(...)` |
| Encapsulamento | Dados `private`, acesso só por métodos. | Atributos de `Lancamento` |
| Herança | A filha ganha o que a mãe tem (`extends`). | `Receita extends Lancamento` |
| `super` | Chama o construtor da mãe. | Construtores das filhas |
| Classe abstrata | Molde incompleto, não aceita `new`. | `abstract class Lancamento` |
| Método abstrato | Método sem corpo que a filha é obrigada a escrever. | `getTipo()`, `getValorNoSaldo()` |
| Polimorfismo | Mesma chamada, resposta diferente por classe. | `calcularSaldo()` |
| `@Override` | "Estou reescrevendo um método da mãe." | `Receita`, `Despesa`, `DespesaParcelada` |
| Interface | Contrato de métodos (`implements`). | `Comparable`, `EventHandler` |
| `static` | Pertence à classe, usa sem `new`. | `Formatador.formatarMoeda` |
| `final` | Não muda mais (constante). | `Receita.TIPO` |
| Exceção | Aviso de erro que pode ser tratado. | `LancamentoInvalidoException` |
| `throw` / `throws` | Dispara o erro / avisa que pode disparar. | Construtor de `Lancamento` |
| `try` / `catch` / `finally` | Tenta / trata o erro / roda sempre. | `adicionarLancamento()`, `ler()` |
| `ArrayList` | Lista que cresce sozinha. | `ControleFinanceiro` |
| `TreeMap` | Pares chave → valor, em ordem. | `calcularDespesasPorCategoria()` |
| CSV | Texto com uma linha por registro e campos separados por `;`. | `dados/lancamentos.csv` |
| `Stage` / `Scene` | A janela / o conteúdo dela. | `start()` |
| `handle` | Método que recebe todos os cliques. | `TelaPrincipal` |
| `getSource()` | Diz qual componente gerou o evento. | Dentro do `handle` |
