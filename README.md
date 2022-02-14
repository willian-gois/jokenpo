# Jokenpo

---

### Sobre
Desafio desenvolvido baseado no jogo [Jokenpo](https://dojopuzzles.com/problems/jokenpo/), em que dado dois jogadores e seus movimentos, o programa calcula o resultado da partida e o jogador vitorioso.

Apesar da simplicidade das regras de negócio envolvidas, foi possível elaborar um desafio que embarca algumas práticas de desenvolvimento de software.

---

### Requisitos técnicos

* Solução mais simples possível;
* Execução em ambiente Unix;
* Implementação de testes automatizados.

---

### Princípios técnicos
* Práticas de Clean Code;
  * Desenvolvimento seguindo os princípios SOLID;
  * Seguindo conceitos de OO: coesão, encapsulamento e baixo acoplamento;
  * Design patterns aplicados: Builder;
  * Fail fast;
  * Funções puras.
* Testes de unidade/unitários (com JUnit 5);
* Gerenciamento de dependêcias (com Maven).

---

### Como executar
``` bash
# Clonar o repositório
git clone https://github.com/willian-gois/jokenpo

# Entrar na raíz do projeto
cd jokenpo

# Instalar dependências Maven, compilar e rodar testes
./mvnw clean install

# Executar aplicação com os parâmetros da partida
./mvnw exec:java -Dexec.args="-jogadorA John Doe -jogadaA papel -jogadorB Jane Doe -jogadaB tesoura"
```

<div align="center">
  <sub>Desenvolvido com ❤ por <a href="https://github.com/willian-gois">Willian Gois</a>.</sub>
</div>