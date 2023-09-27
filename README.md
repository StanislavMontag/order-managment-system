# Order Management System API

This repository contains the source code for an order management system API. It provides endpoints for creating and retrieving orders, searching orders by product name, and updating order line quantities.

## Table of Contents

- [API Endpoints](#api-endpoints)
- [Usage](#usage)

## API Endpoints

| Method | Endpoint                                 | Description                                                  | Request Params          | Example                           |
|--------|------------------------------------------|--------------------------------------------------------------|-------------------------|-----------------------------------|
| POST   | /api/customers                           | Create a new customer                                        | JSON body with customer details | `{ "fullName": "John Doe", "email": "john.doe@example.com", "telephone": "1234567890", "registrationCode": "ABC123" }` |
| POST   | /api/orders                              | Create a new order                                           | JSON body with order details | `{ "customerId": 1, "orderLines": [ { "productId": 1, "quantity": 5 } ] }` |
| GET    | /api/orders/by-date                      | Retrieve orders by submission date                           | date | `/api/orders/by-date?date=2023-09-26` |
| GET    | /api/orders/by-product-name              | Search orders by product name                                | productName | `/api/orders/by-product-name?productName=ExampleProduct` |
| PATCH  | /api/orders/{orderId}/order-lines/{orderLineId} | Update quantity of products in an order line              | orderId, orderLineId, quantity | `/api/orders/1/order-lines/2?quantity=15` |
| POST   | /api/products                            | Create a new product                                         | JSON body with product details | `{ "name": "Example Product", "skuCode": "EXMPL123", "unitPrice": 10.0 }` |

## Usage

The Order Management System API allows users to manage orders and related entities. Users can create new customers, products, and orders. They can also retrieve orders based on submission date, search for orders by product name, and update the quantity of products in an order line.
