## Transaction management in micro-service

In microservices, transaction management is tricky because a single business
process may span multiple services, with its own database — and we can’t use
a single ACID transaction across them, like in a monolith.

* Instead, we use distributed transaction patterns to keep data consistent.

## Common Approaches

### A. Avoid Distributed Transactions (Best Practice)

* Keep each service transaction local.
* Design business flows so each service completes its own transaction independently.
* Use event-driven communication to coordinate with other services

### B. Saga Pattern (Most popular)
* **1. Choreography Saga (event-driven pattern)**
* **2. Orchestration Saga (central coordinator)**
* [SAGA.md](SAGA.md)

### C. Two-Phase Commit (2PC)

* Rare in microservices because:Slower