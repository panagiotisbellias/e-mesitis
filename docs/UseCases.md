# 🏠 E Mesitis – Use Case Specification

**Project:** E Mesitis (Real Estate Rental Management System)  
**Author:** Panagiotis Bellias  
**Version:** 1.1  
**Date:** 2025-11-02  
**Repository:** [github.com/panagiotisbellias/e-mesitis](https://github.com/panagiotisbellias/e-mesitis)

---

## 1. Purpose

This document describes the functional use cases for the *E Mesitis* application.  
The system manages real estate rental listings, providing functionality for property owners (landlords), agents (mesites), and tenants to register, view, and analyze available residences.

---

## 2. System Overview

**E Mesitis** is a Java-based desktop application designed to support real estate rental management.  
It provides:
- Data models for different types of residences (`Apartment`, `DetachedHouse`).
- Basic CRUD (Create, Read, Update, Delete) operations on property listings.
- Search and statistical analysis of rental data.

---

## 3. Actors

| Actor               | Description                                                                             |
|:--------------------|:----------------------------------------------------------------------------------------|
| **Landlord**        | Owns and registers residences for rent.                                                 |
| **Agent (Mesitis)** | Manages and updates property listings, calculates insights, and handles client queries. |
| **Tenant**          | Searches and views available residences to rent.                                        |
| **System**          | Performs automated operations such as filtering, calculations, and data validation.     |

---

## 4. Implementation Status Summary

|  ID  | Title                                   | Implementation Status | Progress Notes                                                                             |
|:----:|:----------------------------------------|:----------------------|:-------------------------------------------------------------------------------------------|
| UC1  | Register a New Residence                | ✅ Implemented         | Core CLI logic and logger added; residence creation and registration verified via console. |
| UC2  | View Available Residences               | ✅ Implemented         | Display layer not yet integrated                                                           |
| UC3  | Search Residence by Criteria            | ✅ Implemented         | To be implemented after collection logic                                                   |
| UC4  | Update Residence Information            | 🔜 *Planned*          | Requires editable list structure                                                           |
| UC5  | Delete Residence                        | 🔜 *Planned*          | Depends on list management UI                                                              |
| UC6  | Calculate Average Rent per Municipality | 🔜 *Planned*          | Logic to be added in service layer                                                         |
| UC7  | Display Detailed Residence Info         | 🔜 *Planned*          | To be added once viewing logic is complete                                                 |
| UC8  | Generate Rental Report                  | ❌ *Not Started*       | Future extension                                                                           |
| UC9  | Recommend Residences                    | ❌ *Not Started*       | Future AI/logic module                                                                     |
| UC10 | Save/Load Data                          | ❌ *Not Started*       | Will require persistence mechanism                                                         |

---

## 5. Use Case Diagram

> *(Optional for later – attach UML Use Case Diagram: “E-Mesitis-UseCases.png”)*

Actors:  
Landlord → (Register Residence)  
Agent → (Update / Delete Residence / Calculate Average Rent)  
Tenant → (View / Search / View Details)

---

## 6. Detailed Use Cases

---

### **UC1 – Register a New Residence**
**Primary Actor:** Landlord / Agent  
**Goal:** Add a new property listing to the system.  
**Implementation Status:** ✅ *Implemented*   
**Progress Notes:**   
- CLI-based flow operational for both Apartment and DetachedHouse.
- Validation and persistence planned for next milestone.
**Preconditions:** User has access to the system via CLI.
**Main Flow:**
1. System logs message: “=== Register a New Residence ===”
2. User selects residence type (Apartment or Detached House).
3. System prompts for details (municipality, area, construction year, bedrooms, bathrooms, rent).
4. For Apartment: input includes floor, parking, warehouse.
   For DetachedHouse: input includes floors, plot area, fireplace.
5. System creates a new residence object and stores it in memory.
6. System logs successful registration message.
**Postconditions:** Residence is registered and stored in the current runtime session.

---

### **UC2 – View Available Residences**
**Primary Actor:** Tenant / Agent  
**Goal:** Browse all available property listings.  
**Implementation Status:** ✅ *Implemented*  
**Progress Notes:** To be developed after list management logic.  
**Main Flow:**
1. User requests list of all residences.
2. System retrieves all `Residence` instances.
3. Details (area, municipality, rent, etc.) are displayed in a table or console view.  
**Postconditions:** User can see basic information for each property.

---

### **UC3 – Search Residence by Criteria**
**Primary Actor:** Tenant  
**Goal:** Find properties matching user criteria.  
**Implementation Status:** ✅ *Implemented*  
**Progress Notes:** Filtering logic to be implemented in data service layer.  
**Preconditions:** Residences exist in the system.  
**Main Flow:**
1. User enters search criteria (municipality, max price, bedrooms, etc.).
2. System filters the `Residence` collection.
3. Matching results are displayed.
**Alternative Flow:**
- No matches found → system displays message *“No results found.”*
**Postconditions:** Matching residences are presented to the user.

---

### **UC4 – Update Residence Information**
**Primary Actor:** Agent / Landlord  
**Goal:** Modify existing property details.  
**Implementation Status:** 🔜 *Planned*  
**Progress Notes:** Requires list index mapping or ID field per residence.  
**Main Flow:**
1. User selects a residence to update.
2. System displays current property details.
3. User edits one or more attributes (e.g. rental price).
4. System applies changes via setters.
**Postconditions:** Residence information is updated.

---

### **UC5 – Delete Residence**
**Primary Actor:** Agent / Landlord  
**Goal:** Remove a property listing.  
**Implementation Status:** 🔜 *Planned*  
**Progress Notes:** Depends on collection data structure (ArrayList or similar).  
**Main Flow:**
1. User selects the residence to delete.
2. System confirms deletion.
3. Residence is removed from active listings.
**Postconditions:** Residence is no longer visible to users.

---

### **UC6 – Calculate Average Rent per Municipality**
**Primary Actor:** Agent  
**Goal:** Retrieve average rental prices grouped by municipality.  
**Implementation Status:** 🔜 *Planned*  
**Progress Notes:** Will require an aggregation method across all residences.  
**Main Flow:**
1. System iterates through all residences.
2. Groups them by municipality.
3. Computes the mean rental price per group.
4. Displays results in tabular form.  
**Postconditions:** Agent obtains insights on rental pricing trends.

---

### **UC7 – Display Detailed Residence Info**
**Primary Actor:** Tenant  
**Goal:** View full property details.  
**Implementation Status:** 🔜 *Planned*  
**Progress Notes:** To be tied to “View Available Residences” feature.  
**Main Flow:**
1. User selects a property from the list.
2. System displays detailed attributes including type, area, construction year, and special features.  
**Postconditions:** The user can evaluate whether the property meets their needs.

---

## 7. Non-Functional Notes
- The system should be designed for easy extension to persistence (database or file-based storage).
- Input validation is mandatory for numeric and string fields.
- The design follows the **Open/Closed Principle** to allow adding new `Residence` subclasses in the future.

---

## 8. Future Use Cases (Planned)

|  ID  | Title                  | Description                                            | Implementation Status | Notes                                             |
|:----:|:-----------------------|:-------------------------------------------------------|:----------------------|:--------------------------------------------------|
| UC8  | Generate Rental Report | Export summary (PDF/CSV) of all residences.            | ❌ *Not Started*       | Reporting planned for Phase 2                     |
| UC9  | Recommend Residences   | Suggest properties based on user preferences.          | ❌ *Not Started*       | To integrate with recommendation module           |
| UC10 | Save/Load Data         | Persist and retrieve listings from a database or file. | ❌ *Not Started*       | Will require serialization or JDBC implementation |

---

## 9. References
- **Source Code:** `src/e/mesitis`  
- **Main Entry Point:** `EMesitis.java`  
- **Data Model:** `Residence`, `Apartment`, `DetachedHouse`

---

> 📄 *This document is version-controlled with the project source code. Update implementation status regularly as features are developed.*
