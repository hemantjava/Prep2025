# Tell me something about your self
"My name is Hemant Kumar Sahu, over 8 years of experience in designing, developing, and leading Java-based applications.
I specialize in Java 8/11/17,21, Spring Boot, Microservices, Kafka, AWS Cloud,
and containerization with Docker and Kubernetes environments
# Project: Text Extraction Service (TES) – Healthcare Domain
->
TES is an OCR-based microservice that extracts structured information from scanned PDF documents (Test Requisition Forms
contains blood cancer patients data) using Amazon Textract ML Service. It’s part of an event-driven microservices
architecture with four services:
MFAX, TES, AUDIT, and SFDC.

Flow:
MFAX: uploads scanned patient documents to Amazon S3 and sends an inbound event that event contains
(S3 URL, tracking ID, document type).

TES: consumes the event, uses Amazon Textract service to extract text, in the form tables,form,blocks,lines,world data 
with confidence scores here we are applies validation and business rules, maps it to a Canonical Data Model (CDM),
and publishes the CDM to the outbound queue.

SFDC: listens to the outbound queue and displays the data in the Salesforce portal for end users.

AUDIT: logs and tracks processing activities.

Purpose:
To collect structured data from patients, store it in a data lake, and support analytics for
early-stage cancer symptoms detection and research.

Key Tech:
Java, Spring Boot, Amazon S3, Amazon Textract, SQS/SNS (event queues), Microservices.

My Roles and responsibility:-
------------------------------ 
● Involved in all phases of project SDLC Agile Methodology.
● Responsible for writing the client specific rest API and Exception Handling, Business Exception Handling.
● Responsible for configuring the new Services and Controllers as well.
● Responsible for writing the Junit 5 test cases with Mockito and Code Coverage.
● Responsible for swagger documentation for exposing resources to the client.
● Responsible for writing Bean validations (Controllers and Services) and Code validation.
● Responsible for deployment in lower environments using codeFresh(CI/CD tool);