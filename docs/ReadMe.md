# Intro

[Click here](#ask-for-collaborator-access) to go to the section "Ask for Collaborator Access".

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