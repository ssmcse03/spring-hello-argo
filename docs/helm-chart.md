# Spring Hello Application with Helm Chart

This repository contains the `spring-hello` application, which is managed using Argo CD and deployed using a Helm chart.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Steps to Upgrade to Helm Chart](#steps-to-upgrade-to-helm-chart)
  - [Step 1: Create a Helm Chart](#step-1-create-a-helm-chart)
  - [Step 2: Customize the Helm Chart](#step-2-customize-the-helm-chart)
  - [Step 3: Push the Helm Chart to a Repository](#step-3-push-the-helm-chart-to-a-repository)
  - [Step 4: Update Argo CD Application](#step-4-update-argo-cd-application)
  - [Step 5: Apply the Updated Argo CD Application](#step-5-apply-the-updated-application)
  - [Step 6: Verify the Deployment](#step-6-verify-the-deployment)

---

## Overview

The `spring-hello` application is a simple Spring Boot application deployed on Kubernetes. This guide explains how to upgrade the deployment to use a Helm chart for better configurability and management.

---

## Steps to Upgrade to Helm Chart

### Step 1: Create a Helm Chart

1. Navigate to your project directory:
   ```bash
   cd C:/ado/learning/spring-hello-argo
2. Create a Helm chart:  
   * This will generate a directory structure under `spring-hello-chart/`.
   ```bash
   helm create spring-hello-chart
---
### Step 2: Customize the Helm Chart  
 * Replace the default `templates/` files with your Kubernetes manifests:
   - Copy `deployment.yaml` to `deployment.yaml`.
   - Copy `service.yaml` to `service.yaml`.
  * Update `values.yaml` to include configurable parameters:  

 * Update `deployment.yaml` to use Helm values:

 * Update `service.yaml` to use Helm values:  
 ---
 
 ### Step 3: Push the Helm Chart to a Repository  
* Package the Helm chart:  

   ```bash
   helm package spring-hello-chart
* Push the chart to a Helm repository (e.g., GitHub Pages, ChartMuseum, or Artifact Hub).  

---


