# 🏗️ Architecture Lab

Ce dépôt est un **laboratoire d’architectures logicielles**.  
Il contient des implémentations pratiques en **Java (Spring Boot)** et **Angular**, pour comparer différentes approches.

## 📂 Organisation
- `java-springboot/`
  - `layered-architecture/` → architecture en couches (classique)
  - `hexagonal-architecture/` → Ports & Adapters
  - `clean-architecture/` → séparation stricte Use Case / Domain / Infra
  - `microservices/` → plusieurs services indépendants
- `angular/`
  - `layered-architecture/` → composants + services
  - `feature-based-architecture/` → découpage par modules
  - `clean-architecture-frontend/` → domain/application/infrastructure/presentation
  - `ngrx-event-driven/` → gestion d’état avec NgRx
- `docs/` → schémas et comparatifs

## 🎯 Objectif
Montrer comment une **même application CRUD** (ex : gestion de produits) peut être déclinée en plusieurs architectures.

## ✅ Exemple à venir
- Java Spring Boot CRUD avec architecture en couches
- Angular CRUD avec architecture feature-based
