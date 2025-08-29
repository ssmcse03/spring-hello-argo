# Spring Boot Application Deployment on Kubernetes with Argo CD

This project demonstrates how to build, dockerize, and deploy a **Spring Boot** application on **Kubernetes** using **Argo CD**.

---

## 📌 Prerequisites
- Java 11 or later
- Maven 3.6+
- Docker
- kubectl
- Minikube / Kind (for local Kubernetes cluster)
- Argo CD installed in Kubernetes

---

## 🚀 Build the Application
```bash
mvn clean package -DskipTests
```
The JAR file will be generated under `target/demo-0.0.1-SNAPSHOT.jar`.

---

## 🐳 Dockerize the Application

### 1. Build Docker Image (local only)
```bash
docker build -t demo-app:latest .
```

### 2. Run Docker Container Locally
```bash
docker run -d -p 8080:8080 --name demo-app demo-app:latest
```
Access the app at: [http://localhost:8080/hello](http://localhost:8080/hello)

> ⚠️ If you want to push your image to a registry (like Docker Hub), you must create an account and tag the image as `your-username/demo-app:latest`.

---

## ☸️ Deploy to Kubernetes

### 1. Create Deployment
`k8s/deployment.yaml`
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: demo-app
  labels:
    app: demo-app
spec:
  replicas: 2
  selector:
    matchLabels:
      app: demo-app
  template:
    metadata:
      labels:
        app: demo-app
    spec:
      containers:
        - name: demo-app
          image: demo-app:latest
          ports:
            - containerPort: 8080
```

### 2. Create Service
`k8s/service.yaml`
```yaml
apiVersion: v1
kind: Service
metadata:
  name: demo-service
spec:
  type: NodePort
  selector:
    app: demo-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
      nodePort: 30007
```

### 3. Apply Manifests
```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

Check resources:
```bash
kubectl get pods
kubectl get svc
```

Access the application:
```bash
minikube service demo-service
```
Or
```bash
curl http://localhost:30007/hello
```

---

## 🔄 Deploy with Argo CD

### 1. Create Argo CD Application
`k8s/app.yaml`
```yaml
apiVersion: argoproj.io/v1alpha1
kind: Application
metadata:
  name: demo-app
  namespace: argocd
spec:
  project: default
  source:
    repoURL: https://github.com/your-username/your-repo.git
    targetRevision: HEAD
    path: k8s
  destination:
    server: https://kubernetes.default.svc
    namespace: default
  syncPolicy:
    automated:
      prune: true
      selfHeal: true
```

### 2. Apply Argo CD Application
```bash
kubectl apply -f k8s/app.yaml -n argocd
```

### 3. Access Argo CD UI
```bash
kubectl port-forward svc/argocd-server -n argocd 8080:443
```


```
Access Argo CD UI using NodePort Serveice rather than port forward.

kubectl patch svc argocd-server -n argocd -p '{"spec": {"type": "NodePort"}}'
kubectl get svc argocd-server -n argocd
NAME            TYPE       CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
argocd-server   NodePort   10.43.135.196   <none>        80:31067/TCP,443:30558/TCP   7m7s
```

Get initial admin password:
```bash
kubectl get secret argocd-initial-admin-secret -n argocd -o jsonpath="{.data.password}" | base64 -d
```


Login at: [https://localhost:8080](https://localhost:8080)

---

## ✅ Summary
1. Build Spring Boot JAR with Maven.
2. Build Docker image and run locally or push to Docker Hub.
3. Deploy with Kubernetes manifests.
4. Use Argo CD for GitOps continuous deployment.

---

🎉 Your Spring Boot app is now running on Kubernetes with Argo CD!
