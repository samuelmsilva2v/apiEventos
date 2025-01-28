# API Eventos
**API RESTful para agendamento de eventos e gerenciamento de participantes.**

Essa API consiste em um sistema de agendamento de eventos projetado para ser gerenciar e controlar os participantes. O sistema permite que os usuários realizem as seguintes ações:
- Criação, exclusão, edição e consulta de eventos.
- Filtros de consulta para os eventos.
- Registro de participantes nos eventos.
- Exclusão, edição e consulta de participantes.
- Filtros de consulta para participantes.

## Tecnologias utilizadas:
- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL**
- **Swagger**
- **Lombok**
- **Model Mapper**
- **Bean Validation (javax.validation)**
- **Docker**
- **RabbitMQ**

## Endpoints
#### Participante
| Método | Endpoint                                               | Descrição                                      |
|--------|--------------------------------------------------------|------------------------------------------------|
| POST   | `/api/partipantes`                                     | Registra um novo participante                  |
| GET    | `/api/partipantes`                                     | Consulta todas os participantes                |
| GET    | `/api/partipantes/{id}`                                | Consulta um participante através do ID         |
| PUT    | `/api/partipantes/{id}`                                | Edita um participante                          |
| GET    | `/api/partipantes/evento/{eventoId}`                   | Consulta os participantes de um evento         |
| GET    | `/api/partipantes/evento/{eventoId}/count`             | Contabiliza todos os participantes registrados |
| DELETE | `/api/partipantes/evento/{eventoId}/participante/{id}` | Exclui um partipante de um determinado evento  |

#### Eventos
| Método | Endpoint                                    | Descrição                                           |
|--------|---------------------------------------------|-----------------------------------------------------|
| POST   | `/api/eventos`                              | Registra um novo evento                             |
| GET    | `/api/eventos`                              | Consulta todas os eventos                           |
| GET    | `/api/eventos/{id}`                         | Consulta um evento através do ID                    |
| PUT    | `/api/eventos/{id}`                         | Atualiza um evento                                  |
| DELETE | `/api/eventos/{id}`                         | Exclui um evento                                    |
| GET    | `/api/eventos/status`                       | Filtra e consulta os eventos por status             |
| GET    | `/api/eventos/period/{startDate}/{endDate}` | Filtra e consulta os eventos em um período de tempo |
| GET    | `/api/evento/count`                         | Contabiliza todos os eventos registrados            |

## Instalação:

##### Construindo a imagem no Docker:
#### 1. No terminal, navegue até a pasta do projeto.
#### 2. Execute o comando abaixo para criar a imagem Docker:

```bash
docker build -t apieventos .
```
##### Executando o container
```bash
docker-compose up -d
```
