# Intro

[Click here](#ask-for-collaborator-access) to go to the section "Ask for Collaborator Access".

---


Install the Argo CD on ssm-64-2

kubectl config use-context rancher-desktop

kubectl create namespace argocd

kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

(optional) kubectl port-forward svc/argocd-server -n argocd 8080:443

kubectl patch svc argocd-server -n argocd -p '{"spec": {"type": "NodePort"}}'

kubectl get svc argocd-server -n argocd 

NAME            TYPE       CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE 

argocd-server   NodePort   10.43.135.196   <none>        80:31067/TCP,443:30558/TCP   7m7s 

https://localhost:30558

User - admin

kubectl get secret argocd-initial-admin-secret -n argocd -o jsonpath="{.data.password}" | base64 -d 

Ht5**********nzM


Create the jar
cd spring-hello-argo\source\app
mvn clean install

spring-hello-0.0.1-SNAPSHOT.jar create at \spring-hello-argo\source\app\target

Create Image
cd to \spring-hello-argo where Dockerfile available

docker build -t spring-hello:1.0 .

kubectl apply -f spring-hello-app.yaml -n argocd

using argo-app yaml file

kubectl apply -f ./argo-apps/spring-hello-app.yaml -n argocd

using different namespace

first creat name space

kubectl create namespace spring-hello-demo-1



PS C:\ado\learning> git clone https://github.com/ssmcse03/spring-hello-argo.git


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