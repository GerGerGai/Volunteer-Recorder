# UBC IMPACT CLUB APP

### What will the application do:

A few friends and I are starting a club called UBC IMPACT, 
helping slum kids in Kenya. Our club will consist of two 
departments: Education and Funding. For the Education part, 
we will have UBC volunteers from different majors help answer 
academic questions from Kenya high school students, and we will 
record teaching videos or find free resources online to address 
certain topics that students need clarifications on. For the Funding 
part, we will organize fundraiser events to help them resolve 
financial issues. I am designing this application to help us save 
and update all the information from the Kenya and UBC sides for our 
Education Department.

### Who will use this app:
- Kenya high school students. The students are coming from Korogocho, 
which is one of the largest slum regions in Kenya. They join our club with 
academic confusion and financial issues. All the academic problems they are 
encountering will be saved into this application and accessible by UBC volunteers.
- UBC volunteers. These are academically strong and kind-hearted UBC students 
coming from different majors. Our application will save their information 
for directors to match them with Kenyan students. They can also access all 
the data from Kenya independently and spot the issues they can help most.


### Why is this project of interest to me:
- There is a huge gap in accessible education in developing countries, 
particularly for science education in marginalized communities. 
Our members are passionate about narrowing that gap on behalf of 
the UBC Science Community.
- After two months of planning, we just realized that this is a complicated 
thing to do, and a part of the reason is that there is so much information 
to process and match from both sides that human brains cannot manage. 
This app will help us a lot in terms of saving, updating and matching 
information and resources.

### User Stories
- As a Kenya student, I want to be able to 
  add my academic confusion to the questions list
- As a Kenya student, I want to be able to view a list of all the UBC volunteers.
- As a new Kenya student, I want to be able to add myself to the system.
- As a UBC volunteer, I want to be able to view a list of all the Kenyan students.
- As a UBC volunteer, I want to be able to remove a question from the questions list
and add it to the questionBeingAnswered list if I'm working on it.
- As a UBC volunteer, I want to be able to remove the academic confusion 
that I resolved from the beingAnsweredList.
- As a Club Director, I want to be able to know the number of volunteers we have
- As a Club Director, I want to know the number of Kenyan students we have.
- As a Club Director, I want to be able to add a volunteer into our system.
- As a general user, I want to be able to decide whether to save the entire 
state of the application or not when I quit the app.
- As a general user, I want to be able to decide whether to resume the app from 
where they left off or not when I start the application.

### Instructions for Grader
- First, you need to run the ClubAppGUI to open the GUI
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by:
  - First, please click the load button and click yes, this will make everything easier. 
    Otherwise, you have to start from an empty system and adding elements in my system requires some time.
  - To add volunteers to the system, click the Add button.
  - Type in the four inputs:
    - id: must be an integer
    - major: must be exactly the same as one of the four options
    - name: a string
    - year: an integer from 1 to 7.
  - Then if you enlarge the window a little bit, there will be an Add button in the center (bad design I know :)
  - Click that Add button.
  - Then if you close the add window, you will see the new volunteer's name appears at the end.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by:
  - I have two actions for this actually
  - First action, you click any name shown on the window and then click the remove button. This will remove the volunteer
    from the system.
  - Second action, you click any name shown on the window and then click the info button. This volunteer's detailed information
    will be shown in the TextArea below.
- You can locate my visual component by:
  - I have two actions for this as well:
  - First:
    - If you click the save button and then click yes, there will be a check image popping up.`
  - Second
     - If you make any changes to the system, please click the save button and click yes first. Otherwise, the visual component
       will not show your new changes.
     - Click the bar button. It will show a bar graph counting the number of volunteers in each major.
- You can save the state of my application by: 
  - click the save button and click yes
- You can reload the state of my application by:
  - click the load button and click yes

### Phase 4: Task 2
Wed Nov 29 15:41:24 PST 2023
Event log cleared.



Wed Nov 29 15:41:38 PST 2023
Add a new volunteer with id: 12345



Wed Nov 29 15:41:48 PST 2023
Add a new volunteer with id: 2333



Wed Nov 29 15:41:55 PST 2023
Remove a volunteer with id: 122222

### Phase 4: Task 3
The refactoring I'm going to perform is to create three more classes called: VolunteerList, StudentList and QuestionList
to store the volunteers, students and questions separately in my system. Right now, I'm having a list of volunteers, a list
of students and a list of questions in my education class and perform all the operations on those lists in that class. This causes
a low cohesion in my education class because there are clearly separate clusters if you only draw the UML for education class.
The app will be more cohesive and manageable as time goes by if I refactor different classes to perform operations separately on
students, volunteers and questions. I then only need to call the methods of those list classes in the education class.

