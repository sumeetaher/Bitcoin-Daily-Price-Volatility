# Design_BTCDailyPriceVolatility

The complete deployment design is categorised into two parts:
* Version Release Design
* Production run Design

Tools List
----------

![image](https://user-images.githubusercontent.com/13486101/125206415-d1f5e880-e2a4-11eb-8d3d-45f09325c1d3.png)


Design proposal for Version Release
-----------------------------------

![image](https://user-images.githubusercontent.com/13486101/125205740-63635b80-e2a1-11eb-9e60-19acd9606de9.png)

Version Release Steps:
----------------------
1. Code written is pushed to GitHub.
2. We deploy using Jenkins that takes the following paramters:
     - Git Repository URL
     - Source and target branch
     - Release version, next version and tag
3. The project bundle is then stored to nexus repository


Design proposal for Production run
----------------------------------

![image](https://user-images.githubusercontent.com/13486101/125205787-986fae00-e2a1-11eb-8e0f-9d6dfdb0029c.png)


Job Run Steps:
--------------

1. Jenkins in linked with Ansible and GitHub for deployment of jobs in production environment.
2. We run the configured Jenkins pipeline which then triggers the Ansible job.
3. Ansible job configured with YML playbooks(stored in Github) then executes oozie job from edge node.
4. Once the oozie job is triggered, all the actions mentioned in the workflows are triggered which in turn executes our spark job.


Oozie Sample:
-------------

![image](https://user-images.githubusercontent.com/13486101/125205823-c6ed8900-e2a1-11eb-8bd9-2ab4de8fa86f.png)


![image](https://user-images.githubusercontent.com/13486101/125205826-c9e87980-e2a1-11eb-8757-aeee49f205d3.png)
