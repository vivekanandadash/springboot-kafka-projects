# Spring Boot Kafka Projects

This repository contains multiple Kafka-based microservice practice projects built using **Spring Boot** and **Apache Kafka**. The goal of this repository is to learn and demonstrate real-time communication between microservices using Kafka.

---

## 📌 Projects Included

### 1️⃣ order-service-example

This service acts as a **Producer**.

**Responsibilities**
- Accept API requests
- Send messages to Kafka topics
- Simulate order creation events

**Technologies Used**
- Spring Boot
- Spring Web
- Spring Kafka
- REST API

**Example Flow**
```
Client Request
      ↓
Order Controller
      ↓
Kafka Producer
      ↓
Kafka Topic
```

---

### 2️⃣ notification-service

This service acts as a **Consumer**.

**Responsibilities**
- Listen to Kafka topics
- Consume order messages
- Simulate notification processing

**Technologies Used**
- Spring Boot
- Spring Kafka

**Example Flow**
```
Kafka Topic
      ↓
Kafka Consumer
      ↓
Notification Service
```

---

## 🧠 What I Learned

- Kafka basics
- Producer and Consumer architecture
- Topic creation
- Real-time message flow
- Spring Boot Kafka integration
- JSON message publishing
- Kafka listener configuration
- Microservice communication using Kafka

---

## ⚙️ Technologies

| Technology     | Purpose                        |
|----------------|--------------------------------|
| Java           | Core programming language      |
| Spring Boot    | Application framework          |
| Apache Kafka   | Message broker                 |
| Maven          | Dependency management          |
| REST API       | Client-service communication   |

---

## 🚀 Kafka Commands Used

### Start Zookeeper
```bash
bin/windows/zookeeper-server-start.bat config/zookeeper.properties
```

### Start Kafka Server
```bash
bin/windows/kafka-server-start.bat config/server.properties
```

### Create Topic
```bash
kafka-topics.bat --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
```

### View Topics
```bash
kafka-topics.bat --list --bootstrap-server localhost:9092
```

### Kafka Producer Console
```bash
kafka-console-producer.bat --topic test-topic --bootstrap-server localhost:9092
```

### Kafka Consumer Console
```bash
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

---

## 📂 Repository Structure

```
springboot-kafka-projects/
│
├── order-notification-service/
│   ├── order-service-example/
│   └── notification-service/
│
└── README.md
```

---

## 🎯 Future Improvements

- [ ] DTO-based Kafka messaging
- [ ] Multiple Kafka topics
- [ ] Order status updates
- [ ] Email/SMS notification simulation
- [ ] Kafka with Microservices Architecture
- [ ] Integration with ShopCore Project
- [ ] Retry mechanism
- [ ] Dead Letter Queue (DLQ)
- [ ] Dockerized Kafka setup
- [ ] AWS Kafka deployment

---

## 👨‍💻 Author

**Vivekananda Dash**

Learning Backend Development, Microservices, Kafka, Spring Boot, and Cloud Technologies.
