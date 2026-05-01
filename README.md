# Report AWS IaaS

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring_Boot-Framework-6DB33F?style=for-the-badge&logo=springboot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-316192?style=for-the-badge&logo=postgresql" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Docker-Containers-2496ED?style=for-the-badge&logo=docker" alt="Docker" />
  <img src="https://img.shields.io/badge/AWS-IaaS-FF9900?style=for-the-badge&logo=amazonaws" alt="AWS" />
  <img src="https://img.shields.io/badge/EC2-Compute-FF9900?style=for-the-badge&logo=amazonaws" alt="EC2" />
  <img src="https://img.shields.io/badge/RDS-PostgreSQL-527FFF?style=for-the-badge&logo=amazonaws" alt="RDS" />
</p>

<p align="center">
  <strong>Projeto de estudo e prática de IaaS na AWS</strong><br/>
  Deploy de uma API Spring Boot containerizada em uma instância EC2, conectada a um PostgreSQL privado no Amazon RDS.
</p>

---

## 📌 Sobre o projeto

O **Report AWS IaaS** é um projeto prático voltado ao aprendizado de **Infraestrutura como Serviço (IaaS)** na AWS.

A proposta foi sair do ambiente local e montar, passo a passo, uma arquitetura realista em nuvem contendo:

- **API REST em Spring Boot (Java 21)**
- **Container Docker** com a aplicação
- **Instância EC2** para execução da aplicação
- **Amazon RDS PostgreSQL** em subnet privada
- **VPC customizada**, com separação entre rede pública e privada
- **Security Groups** controlando o tráfego entre aplicação e banco de dados

O projeto simula uma pequena API de relatórios, servindo como laboratório de deploy, redes, segurança e integração entre serviços da AWS.

---

## 🏗️ Arquitetura

<p align="center">
  <img src="https://raw.githubusercontent.com/felipematheus1337/report-aws-iaas/develop/arquitetura.png" alt="Arquitetura do projeto" width="900" />
</p>

### Fluxo da arquitetura

1. O usuário acessa a API via HTTP pela porta **8080**.
2. O tráfego entra na **VPC** por meio do **Internet Gateway**.
3. A requisição chega à **EC2**, localizada em uma **subnet pública**.
4. Dentro da EC2, a aplicação **Spring Boot** roda em um **container Docker**.
5. A aplicação se conecta ao **Amazon RDS PostgreSQL**, alocado em uma **subnet privada**.
6. O banco não fica exposto à internet, sendo acessível apenas pela aplicação, via **Security Groups**.

---

## 🧰 Tecnologias utilizadas

### Backend
<p>
  <img src="https://skillicons.dev/icons?i=java,spring,maven" alt="Backend" />
</p>

### Banco de dados
<p>
  <img src="https://skillicons.dev/icons?i=postgresql" alt="Banco de dados" />
</p>

### Containerização
<p>
  <img src="https://skillicons.dev/icons?i=docker" alt="Docker" />
</p>

### Cloud / Infraestrutura
<p>
  <img src="https://go-skill-icons.vercel.app/api/icons?i=aws" alt="AWS" />
</p>

### Serviços AWS aplicados

- **Amazon EC2** — hospedagem da aplicação
- **Amazon RDS (PostgreSQL)** — banco de dados gerenciado
- **Amazon VPC** — rede virtual privada
- **Subnets públicas e privadas** — separação de camadas
- **Internet Gateway** — acesso externo à aplicação
- **Security Groups** — regras de comunicação e segurança

---

## ⚙️ Funcionalidades

- Criar relatórios via endpoint `POST /api/v1`
- Listar relatórios via endpoint `GET /api/v1`
- Persistir os dados em PostgreSQL
- Executar a aplicação em container Docker
- Consumir banco privado no RDS a partir da EC2

---

## 📡 Endpoints principais

### Criar relatório

```http
POST /api/v1
```

#### Exemplo de payload

```json
{
  "type": "MEDICAL",
  "itens": [
    {
      "author": "Felipe",
      "value": 150.75,
      "date": "2026-04-30T19:30:00"
    },
    {
      "author": "João",
      "value": 89.90,
      "date": "2026-04-30T20:00:00"
    }
  ]
}
```

### Listar relatórios

```http
GET /api/v1
```

---

## 🚀 Como executar localmente

### 1) Clonar o repositório

```bash
git clone https://github.com/felipematheus1337/report-aws-iaas.git
cd report-aws-iaas
```

### 2) Build da imagem Docker

```bash
docker build -t report-iaas .
```

### 3) Executar o container

> Ajuste as variáveis de ambiente conforme seu banco.

```bash
docker run -d \
  --name report-iaas \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/report" \
  -e SPRING_DATASOURCE_USERNAME="report_user" \
  -e SPRING_DATASOURCE_PASSWORD="report_password" \
  report-iaas
```

---

## ☁️ Deploy na AWS

Este projeto foi pensado para praticar um cenário de IaaS realista na AWS.

### Estrutura implantada

- **1 VPC customizada**
- **1 subnet pública** para a EC2
- **2 subnets privadas** para o RDS
- **1 Internet Gateway**
- **Route table pública** para acesso externo da aplicação
- **EC2** executando a imagem Docker do Spring Boot
- **RDS PostgreSQL** privado, acessível apenas pela EC2

### Execução da aplicação na EC2

Exemplo de execução do container em produção/estudo:

```bash
docker run -d \
  --name report-iaas \
  --restart unless-stopped \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://SEU-ENDPOINT-RDS:5432/report" \
  -e SPRING_DATASOURCE_USERNAME="report_user" \
  -e SPRING_DATASOURCE_PASSWORD="SUA_SENHA" \
  kenshiro1337/report-iaas:latest
```

---

## 🔐 Aprendizados praticados

Durante a construção deste projeto, foram praticados conceitos importantes de cloud e infraestrutura, como:

- Criação manual de recursos na AWS
- Organização de rede com **VPC** e **subnets**
- Diferença entre recursos **públicos** e **privados**
- Exposição controlada da aplicação via **porta 8080**
- Restrição de acesso ao banco via **Security Groups**
- Integração entre **EC2 + Docker + RDS**
- Uso de variáveis de ambiente para configuração da aplicação

---

## 📁 Estrutura do projeto

```bash
report-aws-iaas/
├── src/
├── Dockerfile
├── pom.xml
├── arquitetura.png
└── README.md
```

---

## 🎯 Objetivo

Mais do que apenas disponibilizar uma API, este repositório tem como objetivo servir como **projeto de portfólio**, demonstrando conhecimentos práticos em:

- Java e Spring Boot
- Docker
- PostgreSQL
- AWS EC2
- AWS RDS
- Redes na AWS (VPC, subnets, internet gateway, security groups)
- Fundamentos de IaaS

---

## 👨‍💻 Autor

**Felipe Matheus**

- GitHub: [felipematheus1337](https://github.com/felipematheus1337)

---

## ⭐ Observação final

Se este projeto te ajudou ou se você curtiu a proposta, considere deixar uma **estrela** no repositório.

