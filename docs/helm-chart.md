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
   ```
2. Create a Helm chart:  
   * This will generate a directory structure under `spring-hello-chart/`.
   ```bash
   helm create spring-hello-chart
   ```
---
### Step 2: Customize the Helm Chart  
 * Replace the default `templates/` files with your Kubernetes manifests:
   - Copy `k8s/deployment.yaml` to `template/deployment.yaml`.
   - Copy `k8s/service.yaml` to `template/service.yaml`.
  * Update `values.yaml` to include configurable parameters:  
    - use image `version`, `replicas`, port etc configurable from `values.yaml`

 * Update `deployment.yaml` to use Helm values:
   - Use {{ .Values.replicas }} and {{ .Chart.Name }}
 * Update `service.yaml` to use Helm values:  
 ---
 
 ### Step 3: Push the Helm Chart to a Repository  

* Commit and push your changes into github repo  

spring-hello-argo/
├── argo-apps/
│   └── spring-hello-app.yaml
├── spring-hello-chart/
│   ├── templates/
│   │   └── deployment.yaml
│   └── values.yaml
├── source/
│   └── app/
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/
│       │   │   │   └── com/
│       │   │   │       └── example/
│       │   │   │           └── demo/
│       │   │   │               └── Application.java
│       │   │   └── resources/
│       │   │       ├── application.properties
│       │   │       └── names.txt



* Package the Helm chart:  
   * This generates a `{chart-name}.tgz` file (tarball) of your Helm chart in the current directory.  
      Syntax  
         `helm package {path-to-your-chart} {optional-custom-output-dir}{custom-chart-version-override}`  
      Example  
         helm package .\spring-hello-chart\ --destination . --version 1.0.0  
         helm package .\spring-hello-chart\
   
   ```bash
   helm package .\spring-hello-chart
   ```
* Push the chart to a Helm repository (e.g., GitHub Pages, ChartMuseum, or Artifact Hub).  

*where I Pull my Helm chart ADO OCI?*  

### Step 4: Update Argo CD Application  
   * Update `argo-apps/spring-hello-app.yaml` to Use Helm chart
   * Replace `path` field with `chart`
   ```bash
      path: spring-hello-chart
      chart: spring-hello-chart
         helm:
            valueFiles:
            - values.yaml
   ```         
---

### Dry Run before actual Apply    
   * lint to check the sysntax error in the chart
   * Do the dry run - helm template 
   ```bash
       helm lint .\spring-hello-chart\

       helm template .\spring-hello-chart\ --debug > ssm_spring-app_chart_dry_run.yaml
   ```
---


### Step 5: Apply the Updated Argo CD Application  
   * Apply the argo cd updated Application `argo-apps/spring-hello-app.yaml`   
   ```bash
      kubectl apply -f argo-apps/spring-hello-app.yaml -n argocd
   ```
---

### Step 6: Verify the Deployment   
   * Check the Argo CD UI to ensure the application is synced.
   * Verify the resources in the cluster.
   ```bash
      kubectl get pods
      kubectl get svc
   ```

---



<br><br><br><br><br>   

### Example : Push and Pull helm charts to container registry  
   - Push charts to an OCI registry (GHCR, ECR, GCR, ACR)   
         What it is: Helm v3 supports storing charts in OCI registries (like container images). This is modern and great for CI/CD.

      - **Steps (example: GitHub Container Registry—GHCR)**  
      1. **Login to registry**  
         `helm registry login ghcr.io -u YOUR_GITHUB_USERNAME`   
         *#it will ask for a Personal Access Token with package:write scope*  
      2. **Package**  
         `helm package spring-hello-chart`  
      3. **Push to OCI**  
         `helm push spring-hello-chart-0.1.0.tgz oci://ghcr.io/YOUR_GITHUB_USERNAME/charts`  
      4. **Pull/install**  
         *#Direct install (Helm v3.8+):*  
         `helm install myapp oci://ghcr.io/YOUR_GITHUB_USERNAME/charts/spring-hello-chart --version 0.1.0`  
         
         *Or pull first, then install:*  
         `helm pull oci://ghcr.io/YOUR_GITHUB_USERNAME/charts/spring-hello-chart --version 0.1.0`  
         `helm install myapp spring-hello-chart-0.1.0.tgz`  
   *NOTE- With OCI, you don’t use helm repo add; 
   you use helm registry login, helm push, helm pull, and helm install oci://....*







