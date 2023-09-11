# Sistema de Reservas de Hotel

Bem-vindo ao Sistema de Reservas de Hotel, uma aplicação de gerenciamento de reservas e hóspedes desenvolvida em Java. Este projeto permite que você mantenha um registro de reservas em um hotel, bem como informações sobre os hóspedes.

## Funcionalidades

- **Busca de Reservas:** Você pode buscar reservas pelo número da reserva ou pelo nome do hóspede.
- **Visualização de Dados:** Exiba informações detalhadas sobre as reservas e os hóspedes.
- **Edição de Reservas:** Atualize as informações das reservas, como datas, valores e formas de pagamento.
- **Exclusão de Dados:** Exclua reservas e hóspedes do sistema.
- **Interface Gráfica:** A aplicação possui uma interface gráfica amigável para facilitar a interação do usuário.

## Requisitos

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Banco de Dados MySQL](https://www.mysql.com/)

## Configuração do Banco de Dados

Certifique-se de configurar corretamente a conexão com o banco de dados MySQL antes de executar a aplicação. Você pode fazer isso editando as configurações de conexão no arquivo `src/jdbc/controller/ConnectionFactory.java`.

```java
// Configurações do banco de dados
private static final String URL = "jdbc:mysql://localhost:3306/nome_do_seu_banco";
private static final String USUARIO = "seu_usuario";
private static final String SENHA = "sua_senha";

