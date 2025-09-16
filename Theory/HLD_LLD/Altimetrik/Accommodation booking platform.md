Here is a **clean High-Level Design (HLD)** and **Low-Level Design (LLD)** focused on the **Accommodation Booking Platform** based on your requirements:

---

## ✅ High-Level Design (HLD)

### 🎯 Scope:

* Users can **search for accommodations available on the current date** only.
* Users can **book accommodations for today**.
* No future date bookings allowed.
* Skip Authentication & User Management.

---

### 🏗️ Main Components:

1. 🔍 **Search Service**

    * Search available accommodations for today.
    * Filters: Location, Type, Price range.

2. 🏨 **Booking Service**

    * Book available accommodations for today.
    * Checks availability before booking.

3. 📦 **Database**

    * Stores accommodation data and booking records.

4. ⚡ **API Gateway**

    * Expose REST endpoints for Search and Booking.

---

### 🚀 Flow Diagram:

```plaintext
[User] → [API Gateway]
             ↓
   ┌───────────────┐    ┌──────────────┐
   │ Search Service│ →  │ DB (Postgres)│
   └───────────────┘    └──────────────┘
             ↓
   ┌───────────────┐
   │ Booking Service│ → DB (Postgres)
   └───────────────┘
```

---

## ✅ Low-Level Design (LLD)

### 1️⃣ Database Schema

#### Accommodations Table

| Column           | Type    | Notes                     |
| ---------------- | ------- | ------------------------- |
| id               | BIGINT  | PK                        |
| name             | VARCHAR | Name of accommodation     |
| location         | VARCHAR | City, Area                |
| type             | VARCHAR | Room type (single, suite) |
| price            | DECIMAL | Price per night           |
| available\_today | BOOLEAN | Is available today        |

#### Bookings Table

| Column            | Type      | Notes                   |
| ----------------- | --------- | ----------------------- |
| id                | BIGINT    | PK                      |
| accommodation\_id | BIGINT    | FK → accommodations(id) |
| booked\_at        | TIMESTAMP | Booking timestamp       |

---

### 2️⃣ REST API Endpoints

#### ➤ Search Available Accommodations

```http
GET /accommodations/search?location=Delhi&type=single&priceMax=5000
```

Response:

```json
[
    {
        "id": 1,
        "name": "Hotel Sunshine",
        "location": "Delhi",
        "type": "single",
        "price": 3000
    }
]
```

#### ➤ Book Accommodation

```http
POST /bookings
{
    "accommodationId": 1
}
```

Response:

```json
{
    "bookingId": 101,
    "status": "CONFIRMED",
    "bookedAt": "2025-09-15T10:00:00"
}
```

---

### 3️⃣ Business Logic

* ✅ Search:

    * Only return accommodations where `available_today = true`.
* ✅ Booking:

    * Check `available_today = true`.
    * Upon successful booking, mark `available_today = false`.

---

### ✅ Scalability Considerations

| Problem                 | Solution                                                   |
| ----------------------- | ---------------------------------------------------------- |
| High Search Volume      | Use Elasticsearch for fast searching                       |
| Booking Race Conditions | Use DB-level locking or optimistic concurrency control     |
| Availability Sync       | Scheduled task resets `available_today = true` at midnight |

---

## ✅ Summary

This is a minimal yet complete design focusing on:

* Search for available accommodations today.
* Booking only for today.
* Simplified data model & APIs.

🚀 Would you like me to generate a sample Spring Boot project skeleton implementing this?
