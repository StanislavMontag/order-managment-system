# Order Management System API

This repository contains the source code for an order management system API. It provides endpoints for creating and retrieving orders, searching orders by product name, and updating order line quantities.

## Table of Contents

- [API Endpoints](#api-endpoints)
- [Usage](#usage)
- [Database Migration and Test Data](#database-migration-and-test-data)
- [Usage Note](#usage-note)

## API Endpoints

| Method | Endpoint                                        | Description                                      | Request Params                    | Example                                                                                                                |
|--------|-------------------------------------------------|--------------------------------------------------|-----------------------------------|------------------------------------------------------------------------------------------------------------------------|
| POST   | /api/customers                                  | Create a new customer                            | JSON body with customer details   | `{ "fullName": "John Doe", "email": "john.doe@example.com", "telephone": "1234567890", "registrationCode": "ABC123" }` |
| POST   | /api/orders                                     | Create a new order                               | JSON body with order details      | `{ "customerId": 1, "orderLines": [ { "productId": 1, "quantity": 5 } ] }`                                             |
| GET    | /api/orders/by-date                             | Retrieve orders by submission date               | date                              | `/api/orders/by-date?date=2023-09-26`                                                                                  |
| GET    | /api/orders/by-product/jpql                     | Search orders by product name using JPQL         | product                           | `/api/orders/by-product/jpql?product=ExampleProduct`                                                                   |
| GET    | /api/orders/by-product/criteria                 | Search orders by product name using Criteria API | product                           | `/api/orders/by-product/criteria?product=ExampleProduct`                                                               |
| PATCH  | /api/orders/{orderId}/order-lines/{orderLineId} | Update quantity of products in an order line     | orderId, orderLineId, newQuantity | `/api/orders/1/order-lines/2?newQuantity=15`                                                                           |
| POST   | /api/products                                   | Create a new product                             | JSON body with product details    | `{ "name": "Example Product", "skuCode": "EXMPL123", "unitPrice": 10.0 }`                                              |

## Usage

The Order Management System API allows users to manage orders and related entities. Users can create new customers, products, and orders. They can also retrieve orders based on submission date, search for orders by product name, and update the quantity of products in an order line. The service uses Liquibase as db migration tool and inserting initial test data. To use search options you need to create order first.

## Database Migration and Test Data

The service employs Liquibase as its database migration tool. Initial test data is also inserted using Liquibase.

## Usage Note

To utilize the search options, it's essential to create an order first.
