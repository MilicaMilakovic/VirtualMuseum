# Virtual Museums
Internet Programming Fullstack Project | University project 2022

- Virtual Museums
    - Angular Frontend
    - Springboot Backend
    - JSP M2 Administrator App

- Virtual Bank 
    - JSP App

- Database
    - MySQL

<hr/>

## Virtual Museums

Once registered and logged in, a user is able to preview over 50 museums, and buy a ticket for a virtual tour of the choosen museum. The ticket is sent to the user's email,
and user will be notified by mail 1 hour before the tour starts, as well as 5 minutes before its end. 
A museum preview includes basic information about the museum - phone number, address, museum type, a brief description, its location on map and a weather forecast
for three randomly chosen cities from the museum's country. 
Users are also able to read the news from arts and culture.

<br/>
<p align="center" >
  <img src="https://user-images.githubusercontent.com/23185716/164044657-b0b8e32d-40b1-44c8-a437-035c80f7919d.gif" width="800px">  
</p>


- Login Page 

<br/>
<p align="center" >
  <img src="https://user-images.githubusercontent.com/23185716/164044009-17a1a615-4cf2-4afb-a239-603f8b7fa411.gif" width="800px">  
</p>

A user can log in only if their account has been approved by administrator. 

## Main App
<br/>

### Museums 

In the museums tab, a user can preview over 50 museums. By clicking on the museum card, user can view details about the choosen museum - its phone number, address, type, description, its location on map, weather for three randomly chosen cities from the museum's country, and currently active virtual tours of that museum, for which they can purchase a ticket. 
Users can also search museums by their name or location.

https://user-images.githubusercontent.com/23185716/164048603-4762a18f-6b05-4854-9c97-7fbe3193f110.mp4


<br/>

### Virtual tour ticket purchase

Users can preview currently active virtual tours and buy a ticket for a specific tour. Users need to provide their virtual credit card info upon purchase. The provided data will be evaluated and the purchase will be approved if there's enough money on the card, entered information is valid and card is enabled. Otherwise, the purchase will be canceled. Once the ticket is purchased, user will receive a mail containing the ticket in PDF format, and the ticket will be shown on their profile, from where they can start the virtual tour. 
Users can also manage their virtual bank accounts - preview their balance, credit card number, transactions and enable/disable the card. These actions are provided in the separate, JSP M2 Virtual Bank application.

https://user-images.githubusercontent.com/23185716/164050763-84978ff6-34fe-429d-a038-12999e60e5a7.mp4

<br/>

### Virtual Tour

User can find all the tickets they have bought on their profile. Each tour has its start time and duration. The virtual tour of a museum consists of 5-10 images of its most famous artwork, and a video. User can start the tour only by clicking on the link of the tour ticket. If they try to start the tour before its start time, or after it has finished, the tour content won't be visible and the corresponding message will be shown instead. Also, users will receive a mail as a reminder 5 minutes before the tour he has bought a ticket for finishes, and one hour before it starts.

https://user-images.githubusercontent.com/23185716/164051437-f7eac5cf-681d-4851-9837-3cfec2492f4a.mp4


<br/>

### News

Users can read news from arts and culture. The articles are provided by consuming a <a href="https://www.huffpost.com/section/arts/feed" title="RSS feed" >RSS feed</a>. 


https://user-images.githubusercontent.com/23185716/165383168-94c077a3-064c-48e4-b7df-44833453c1ad.mp4

</br>

## Administrator


Administrator can add a new museum, or add a tour for a specific museum. They can also view statistics and preview and download logs - users' actions, in PDF format. By clicking on the *Manage users* link,
an administrator is taken to the JSP part of the application, where they can perform CRUD operations on users accounts, approve registration and reset user's password to random value. 

https://user-images.githubusercontent.com/23185716/164053729-3668e1e8-7f51-493d-ad97-d3ce21ee9fad.mp4

## 

All pages are responsive!
<p align="center">
  <img src="https://user-images.githubusercontent.com/23185716/165381634-46e4b32b-b442-4198-9682-d3f00f15ca2b.png" width="230" />
  <img src="https://user-images.githubusercontent.com/23185716/165382194-8096c390-cb12-45bb-9344-38d0f5fd1a88.png" width="230" /> 
  <img src="https://user-images.githubusercontent.com/23185716/165382148-5182abf1-723b-4333-889f-a58f07bb3c74.png" width="230" />
  <img src="https://user-images.githubusercontent.com/23185716/165382225-17b55eb9-20cc-434a-9ed6-f42d5a7984e3.png" width="230" />
</p>




