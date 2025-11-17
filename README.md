# Projeto Roteador IPv4

**Disciplina:** Programação Orientada a Objetos (POO)  
**Instituição:** IFPB - Instituto Federal da Paraíba

---

## Sobre o Projeto

Este projeto acadêmico apresenta uma implementação em Java de um **roteador IPv4 simplificado**, desenvolvido como parte da disciplina de Programação Orientada a Objetos. O sistema modela os principais componentes de um roteador de rede: suas interfaces de rede e a tabela de roteamento que determina o caminho dos pacotes.

A proposta é aplicar na prática conceitos fundamentais de POO, como **encapsulamento**, **composição de objetos**, **uso de coleções** e **modelagem de domínio**, criando uma representação orientada a objetos de elementos reais da infraestrutura de redes.

---

## Arquitetura do Sistema

O sistema é composto por três classes principais que trabalham em conjunto:

### 🔹 Roteador
Representa o dispositivo de rede propriamente dito. Contém um nome identificador, uma tabela de rotas para decisões de encaminhamento e uma coleção de interfaces de rede físicas ou lógicas.

### 🔹 Rota
Modela uma entrada individual da tabela de roteamento, especificando o destino, a máscara de sub-rede, o gateway (próximo salto) e qual interface deve ser utilizada para alcançar determinada rede.

### 🔹 Interface
Representa uma interface de rede do roteador, caracterizada por um nome (como eth0, wlan0) e seu endereço IP associado.

---

## Conceitos de Programação Orientada a Objetos

O projeto demonstra a aplicação prática de diversos princípios de POO:

- **Encapsulamento:** Proteção dos dados internos através de modificadores de acesso e métodos getters/setters
- **Composição:** O roteador é composto por múltiplas rotas e interfaces, estabelecendo relações "tem-um"
- **Abstração:** Modelagem de entidades do mundo real (redes de computadores) em classes Java
- **Uso de Coleções:** Gerenciamento dinâmico de múltiplos objetos através de estruturas como `List<T>`
- **Sobrecarga de Métodos:** Diferentes construtores para flexibilidade na criação de objetos
- **toString():** Representação textual adequada dos objetos para depuração e apresentação

---

## Estrutura do Projeto

```
roteadoripv4/
├── pom.xml                          # Configuração do projeto Maven
├── README.md                        # Documentação do projeto
└── src/
    └── main/
        └── java/
            └── br/edu/com/ifpb/tsi/poo/
                ├── Interface.java   # Classe que representa interfaces de rede
                ├── Rota.java        # Classe que representa entradas de rota
                ├── Roteador.java    # Classe principal do roteador
                └── Main.java        # Classe de demonstração
```

---

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Gerenciador de Build:** Maven
- **Paradigma:** Programação Orientada a Objetos

---

## Contexto Acadêmico

Este trabalho foi desenvolvido como atividade avaliativa para a disciplina de Programação Orientada a Objetos do curso de Tecnologia em Sistemas para Internet do IFPB. O objetivo é consolidar o aprendizado dos conceitos de POO através de um projeto prático que simula cenários reais da área de redes de computadores.

---

## Autores

**Nome:** [Seu Nome]  
**Matrícula:** [Sua Matrícula]  
**Nome:** [Seu Nome]  
**Matrícula:** [Sua Matrícula]  
**Curso:** Tecnologia em Sistemas para Internet (TSI)  
**Instituição:** Instituto Federal da Paraíba (IFPB)

---

## Licença

Este projeto é de uso exclusivamente acadêmico, desenvolvido para fins educacionais na disciplina de Programação Orientada a Objetos.
