# Intro

[Click here](#ask-for-collaborator-access) to go to the section "Ask for Collaborator Access".

---

# Spring Hello Argo - Quick Start Guide

---

## Update and Deploy the Application

1. **Modify the Code**
   - Make your code changes as needed.

2. **Build the JAR**
   ```sh
   cd C:\ado\learning\spring-hello-argo\source\app
   mvn clean install -DskipTests
   ```

3. **Build the Docker Image (version 3.0)**
   ```sh
   cd \learning\spring-hello-argo
   docker build -t spring-hello:3.0 .
   ```

4. **Update Kubernetes Deployment**
   - Edit `k8s/deployment.yaml` to use the new image version `spring-hello:3.0`.

5. **Apply Argo CD Application**
   ```sh
   kubectl apply -f ./argo-apps/spring-hello-app.yaml -n argocd
   ```

---

## Install Argo CD on ssm-64-2

1. **Set Kubernetes Context**
   ```sh
   kubectl config use-context rancher-desktop
   ```

2. **Create Namespace**
   ```sh
   kubectl create namespace argocd
   ```

3. **Install Argo CD**
   ```sh
   kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
   ```

4. **(Optional) Port Forward Argo CD Server**
   ```sh
   kubectl port-forward svc/argocd-server -n argocd 8080:443
   ```

5. **Expose Argo CD Server via NodePort**
   ```sh
   kubectl patch svc argocd-server -n argocd -p '{"spec": {"type": "NodePort"}}'
   kubectl get svc argocd-server -n argocd
   ```

   Example output:
   ```
   NAME            TYPE       CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
   argocd-server   NodePort   10.43.135.196   <none>        80:31067/TCP,443:30558/TCP   7m7s
   ```

6. **Access Argo CD UI**
   - URL: [https://localhost:30558](https://localhost:30558)
   - User: `admin`

7. **Get Initial Admin Password**
   ```sh
   kubectl get secret argocd-initial-admin-secret -n argocd -o jsonpath="{.data.password}" | base64 -d
   ```
   - Example password: `Ht5**********nzM`

---

## Build and Deploy the Application

1. **Build the JAR**
   ```sh
   cd spring-hello-argo\source\app
   mvn clean install
   ```
   - Output: `spring-hello-0.0.1-SNAPSHOT.jar` at `\spring-hello-argo\source\app\target`

2. **Build Docker Image**
   ```sh
   cd \spring-hello-argo
   docker build -t spring-hello:1.0 .
   ```

3. **Apply Kubernetes Manifest**
   ```sh
   kubectl apply -f spring-hello-app.yaml -n argocd
   ```

4. **Using Argo App YAML File**
   ```sh
   kubectl apply -f ./argo-apps/spring-hello-app.yaml -n argocd
   ```

5. **Using a Different Namespace**
   - First, create the namespace:
     ```sh
     kubectl create namespace spring-hello-demo-1
     ```

---

## Clone the Repository

```sh
git clone https://github.com/ssmcse03/spring-hello-argo.git
```

---

## Create the Argo Application

```sh
kubectl apply -f ./argo-apps/spring-hello-app.yaml -n argocd
```

---

## Ask for Collaborator Access

Request the owner of `ssmcse03/spring-hello-argo` to add you as a collaborator with write access.

### Steps:

1. **Log in to GitHub with the Repository Owner Account**
   - Go to [GitHub](https://github.com)
   - Log in as `ssmcse03`

2. **Go to the Repository Settings**
   - Navigate to: [https://github.com/ssmcse03/spring-hello-argo](https://github.com/ssmcse03/spring-hello-argo)
   - Click **Settings**

3. **Add a Collaborator**
   - Click **Collaborators and Teams** (or **Manage Access**)
   - Click **Add people**
   - Enter username: `SatyendraSinghMadhur`
   - Click **Add SatyendraSinghMadhur to this repository**
   - GitHub sends an invitation

4. **Accept the Invitation**
   - Log in as `SatyendraSinghMadhur`
   - Go to **Notifications**
   - Accept the invitation

---

---

## Create the Argo Application

```sh
kubectl apply -f ./argo-apps/spring-hello-app.yaml -n argocd
```

---

## Ask for Collaborator Access

Request the owner of `ssmcse03/spring-hello-argo` to add you as a collaborator with write access.

To allow the user `SatyendraSinghMadhur` to push to the repository, follow these steps:

### Step 1: Log in to GitHub with the Repository Owner Account

- Open a browser and go to [GitHub](https://github.com)
- Log in using the `ssmcse03` account (the owner of `spring-hello-argo`)

### Step 2: Go to the Repository Settings

- Navigate to the repository: [https://github.com/ssmcse03/spring-hello-argo](https://github.com/ssmcse03/spring-hello-argo)
- Click on the **Settings** tab (usually at the top-right of the repo page)

### Step 3: Add a Collaborator

- In the left sidebar, click **Collaborators and Teams** (or sometimes just **Manage Access**)
- Click **Add people**
- Type the username: `SatyendraSinghMadhur`
- Click **Add SatyendraSinghMadhur to this repository**
- GitHub will send an invitation to `SatyendraSinghMadhur`

### Step 4: Accept the Invitation

- Log in to GitHub as `SatyendraSinghMadhur`
- Go to **Notifications** → you will see the invitation
- Click **Accept invitation**