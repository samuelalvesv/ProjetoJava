import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
      // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionario> funcionarios = adicionarFuncionarios();
      
      // 3.2 – Remover o funcionário “João” da lista. 
      funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

      // 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que: 
      //• informação de data deve ser exibido no formato dd/mm/aaaa; 
      //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula. 
      System.out.println("Todos os funcionários:");
      for (Funcionario funcionario : funcionarios)
        System.out.println(funcionario.toString());

      //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
      for (Funcionario funcionario : funcionarios)
        funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.1)));;

      //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
      Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
      for (Funcionario funcionario : funcionarios) {
          String funcao = funcionario.getFuncao();

        if (!funcionariosPorFuncao.containsKey(funcao)) {
              funcionariosPorFuncao.put(funcao, new ArrayList<>());
          }
        
          funcionariosPorFuncao.get(funcao).add(funcionario);
      }

      //3.6 – Imprimir os funcionários, agrupados por função. 
      System.out.println("\nFuncionários agrupados por função:");
      for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
          System.out.println("Função: " + entry.getKey());
          for (Funcionario f : entry.getValue()) {
              System.out.println("  " + f);
          }
      }

      //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
      System.out.println("\nFuncionários que fazem aniversário no mês 10 e 12");
      for (Funcionario funcionario : funcionarios)
        if (funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12)
          System.out.println(funcionario.toString());

      //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade. 
      System.out.println("\nFuncionário com a maior idade:");
      Funcionario funcionarioMaisVelho = funcionarios.get(0);
      for (Funcionario f : funcionarios) {
          if (f.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
              funcionarioMaisVelho = f;
          }
      }
      System.out.println(funcionarioMaisVelho);

      //3.10 – Imprimir a lista de funcionários por ordem alfabética. 
      System.out.println("\nLista de funcionários por ordem alfabética:");
      funcionarios.sort((f1, f2) -> f1.getNome().compareTo(f2.getNome()));
      for (Funcionario funcionario : funcionarios) {
          System.out.println(funcionario);
      }

      //3.11 – Imprimir o total dos salários dos funcionários.
    BigDecimal totalSalarios = funcionarios.stream()
      .map(Funcionario::getSalario)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    String totalFormatado = currencyFormatter.format(totalSalarios);

    System.out.println("\nTotal dos salários dos funcionários: " + totalFormatado);

      //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00. 
      System.out.println("\nQuantos salários mínimos ganha cada funcionário:");
      BigDecimal salarioMinimo = new BigDecimal("1212.00");
      for (Funcionario funcionario : funcionarios) {
          BigDecimal qtdSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
          System.out.println(funcionario.getNome() + " ganha " + qtdSalariosMinimos + " salários mínimos.");
      }
  }

    public static List<Funcionario> adicionarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        return funcionarios;
    }
}
