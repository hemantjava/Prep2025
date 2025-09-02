1). What is AWS ?

Ans: AWS(Amazon Web Service) is a cloud computing service offered by Amazon. 
     AWS lets we build, test, deploy and manage applications and services.
     
     
2). What Is Identity and Access Management (IAM) and How Is It Used?

Ans). Identity and Access Management (IAM) is a web service for securely controlling access to AWS services.
(["IAM lets you manage users, security credentials such as access keys, and permissions that control which AWS 
 resources users and applications can access."])
   4 key concepts: Users, Groups, Roles and Policies/Permissions.

*  	Users: Specific Individuals, can receive personal logins.
*	Groups: Collection of users.
*	Roles: Collection of Policies (DB Read, DB Write).
*	Policies/Permissions: Law level permission to resources(Allow/Deny).

3).  What is Amazon S3 ?

Ans). S3 stands for Simple Storage Service. 
      It is used for data storage over the internet at any time and from anywhere on the web.

4). Amazon SNS ?

Ans). SNS stands for "Simple Notification Service". It is a notification service.
      SNS is a Publisher / Subscriber System.
      Publishing messages to a topic can deliver to many subscribers as different types(SQS, Lambda, Email, SMS).

5). Amazon SQS ?

Ans). SQS stands for "Simple Queue Service". 
      SQS is a Queueing service for message.
In AWS SQS (Simple Queue Service), the delivery semantics depend on the queue type you choose:

1. Standard Queue (default)
   •	At-least-once delivery
   → A message might be delivered more than once (duplicates are possible).
   •	Best-effort ordering
   → The order of messages is not guaranteed.

So not exactly-once, it’s at-least-once.

⸻

2. FIFO Queue (First-In-First-Out)
   •	Exactly-once processing (logical guarantee)
   → A message is delivered once and remains invisible until processed/deleted.
   •	Guaranteed ordering
   → Messages are delivered in the exact order they are sent.

⸻

✅ Summary:
•	Standard Queue → At-least-once delivery (duplicates possible)
•	FIFO Queue → Exactly-once delivery + Ordering
      
6). Amazon Textract ?

Ans). Amazon Textract is a machine learning (ML) service that automatically extracts text,
     from handwriting and scanned documents.
  ----------------------------------------------------    
 (for example: What are the process we do in Textract?
 Amazon Textract offers APIs that detect and extract printed text and handwriting from scanned images of documents, 
 extract structured data such as tables, perform key-value pairing on extracted text.
------------------------------------------------------

7). What is Amazon RDS ?

Ans). RDS stands for Relational Database Service.
      It provides affordable relational databases in the cloud, that is easy to use.
      
8). What is Amazon Secrete manager?
   -> Secrete manager store the credential like DB,Password etc.
   
9). What is AWS lambda?

Ans). AWS lambda is a serverless computing service provided/offered by amazon(AWS), that allow us to run our code without 
      managing servers. With AWS lambda we can simply upload our code and the service takes care of everything else,
      including scaling, patching and monitoring.
   "Patching is the process of updating software, applications, or systems with a "patch" or "fix" to address security 
    vulnerabilities, bugs, or other issues that have been identified. "
---
Here’s a **clear comparison of ECS, EC2, and EKS in AWS** 👇

---
## Clear comparison of ECS, EC2, and EKS in AWS 👇
### 🔹 **1. EC2 (Elastic Compute Cloud)**

* **What it is**: Virtual servers (VMs) in AWS to run any workload (apps, databases, services).
* **Level**: Infrastructure as a Service (IaaS).
* **Use case**: You manage the OS, runtime, scaling, and deployments.
* **Example**: Launching an Ubuntu EC2 instance and manually deploying a Spring Boot app.

---

### 🔹 **2. ECS (Elastic Container Service)**

* **What it is**: AWS-managed **container orchestration service**.
* **Level**: Platform as a Service (PaaS).
* **Use case**: Run Docker containers without managing servers. Can run on:

    * **EC2 mode** → Containers run on your EC2 instances.
    * **Fargate mode** → Serverless; AWS manages compute resources.
* **Example**: Deploying your Spring Boot app in a Docker image to ECS with auto-scaling.

---

### 🔹 **3. EKS (Elastic Kubernetes Service)**

* **What it is**: AWS-managed **Kubernetes service**.
* **Level**: Platform as a Service (but more flexible than ECS).
* **Use case**: Run containers using Kubernetes, if you want Kubernetes-native tools (Helm, CRDs, operators).
* **Example**: Deploying a microservices system with service mesh (Istio/Linkerd) on AWS.

---

### ✅ **Quick Table Comparison**

| Feature           | **EC2**                       | **ECS**                              | **EKS**                                   |
| ----------------- | ----------------------------- | ------------------------------------ | ----------------------------------------- |
| Type              | Virtual Machines (IaaS)       | Container Orchestration (AWS-native) | Managed Kubernetes Orchestration          |
| Abstraction Level | Lowest (you manage infra)     | Medium (AWS manages orchestration)   | High (you get Kubernetes control plane)   |
| Scaling           | Manual or Auto Scaling Groups | Auto-scaling for containers          | Kubernetes-native scaling (HPA, etc.)     |
| Flexibility       | Any workload                  | Only Docker containers               | Any containerized app, Kubernetes tooling |
| Learning Curve    | Low                           | Medium                               | High (need Kubernetes knowledge)          |
| Use Case          | Legacy apps, full control     | AWS-native container workloads       | Kubernetes workloads, hybrid/multi-cloud  |

---

👉 **In short**:

* **EC2** → Use when you want **full control** over servers.
* **ECS** → Use when you want **easy container orchestration** (AWS-native).
* **EKS** → Use when you want **Kubernetes ecosystem & portability**.

---
Got it 👍 Let’s break it down clearly.

---

## 🔹 Primary Index in DynamoDB

* **Every DynamoDB table must have a Primary Key.**
* It uniquely identifies each item in the table.
* Two types:

    1. **Partition Key (Simple Primary Key)** → Single attribute.
    2. **Partition Key + Sort Key (Composite Primary Key)** → Combination of two attributes.

📌 Example:

```json
Table: Users
Primary Key: userId (Partition Key)
```

* Item: `{ "userId": "U101", "name": "Hemant", "email": "hemant@email.com" }`
* Here, `userId` uniquely identifies each user.

If we use composite:

```json
Table: Orders
Primary Key: userId (Partition Key), orderId (Sort Key)
```

* Items:

    * `{ "userId": "U101", "orderId": "O001", "amount": 500 }`
    * `{ "userId": "U101", "orderId": "O002", "amount": 700 }`

👉 Same user (`U101`) can have multiple orders, distinguished by `orderId`.

---

## 🔹 Secondary Index in DynamoDB

* Used to **query data using non-primary key attributes.**
* Two types:

1. **Global Secondary Index (GSI)**

    * Can have a different partition key and sort key than the table.
    * Queries across **all partitions**.
    * Good when you want to query by attributes not in the primary key.

   📌 Example:

   ```json
   Table: Users
   Primary Key: userId
   GSI: email (Partition Key)
   ```

    * You can now quickly query user by `email`, even though it’s not the primary key.

   Query Example:

   ```sql
   Find user where email = 'hemant@email.com'
   ```

2. **Local Secondary Index (LSI)**

    * Shares the **same partition key** as the base table, but has a different sort key.
    * Defined only at table creation.

   📌 Example:

   ```json
   Table: Orders
   Primary Key: userId (Partition Key), orderId (Sort Key)
   LSI: orderDate (Sort Key)
   ```

    * You can now query all orders for `userId = U101` sorted by `orderDate`.

---

## ✅ Summary

* **Primary Index** → Must-have, unique identifier (`PartitionKey` or `PartitionKey+SortKey`).
* **Secondary Index** → Optional, helps query data on other attributes.

    * **GSI** → Different partition key (flexible, powerful).
    * **LSI** → Same partition key, different sort key (defined at table creation).

---
Perfect 🚀 Let’s do it with **AWS SDK for Java v2** (the latest one most people use in Spring Boot & modern Java projects).

---

## ✅ Example: DynamoDB Table with Primary Key + GSI

We’ll create a `Users` table:

* **Primary Key** → `userId` (Partition Key)
* **Global Secondary Index (GSI)** → `email` (Partition Key)

---

### Maven Dependency

```xml
<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>dynamodb</artifactId>
    <version>2.25.6</version> <!-- latest as of 2025 -->
</dependency>
```

---

### Java Code

```java
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Arrays;

public class DynamoDBExample {
    public static void main(String[] args) {
        // 1. Create DynamoDB client
        DynamoDbClient dynamoDb = DynamoDbClient.create();

        // 2. Define table schema
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("Users")
                .attributeDefinitions(
                        AttributeDefinition.builder()
                                .attributeName("userId")
                                .attributeType(ScalarAttributeType.S).build(),
                        AttributeDefinition.builder()
                                .attributeName("email")
                                .attributeType(ScalarAttributeType.S).build()
                )
                .keySchema(KeySchemaElement.builder()
                                .attributeName("userId")
                                .keyType(KeyType.HASH).build() // Partition Key
                )
                // 3. Add Global Secondary Index
                .globalSecondaryIndexes(GlobalSecondaryIndex.builder()
                        .indexName("EmailIndex")
                        .keySchema(KeySchemaElement.builder()
                                        .attributeName("email")
                                        .keyType(KeyType.HASH).build()) // GSI Partition Key
                        .projection(Projection.builder()
                                        .projectionType(ProjectionType.ALL) // include all attributes
                                        .build())
                        .provisionedThroughput(ProvisionedThroughput.builder()
                                        .readCapacityUnits(5L)
                                        .writeCapacityUnits(5L)
                                        .build())
                        .build())
                // 4. Define table capacity
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(5L)
                        .writeCapacityUnits(5L)
                        .build())
                .build();

        // 5. Create the table
        dynamoDb.createTable(request);

        System.out.println("✅ Users table created with GSI on email!");
    }
}
```

---

### ✅ How It Works

* `Primary Key`: `userId`
* `GSI`: `email` → lets you query users by their email.

Example query with SDK:

```java
QueryRequest query = QueryRequest.builder()
        .tableName("Users")
        .indexName("EmailIndex")
        .keyConditionExpression("email = :val")
        .expressionAttributeValues(Map.of(":val",
                AttributeValue.builder().s("hemant@email.com").build()))
        .build();

QueryResponse response = dynamoDb.query(query);
response.items().forEach(System.out::println);
```
## Exponential Backoff (in Queues / Retry Mechanism)

Definition:
Exponential backoff is a retry strategy where, instead of retrying a failed operation immediately or at a fixed interval,
the system waits for an increasing (exponential) amount of time before retrying again.

It is widely used in message queues (SQS, Kafka, RabbitMQ), APIs, and distributed systems to avoid overwhelming 
a service when errors occur.

✅ How It Works

First failure → wait 1s and retry.

Second failure → wait 2s and retry.

Third failure → wait 4s and retry.

Fourth failure → wait 8s and retry.

… and so on.