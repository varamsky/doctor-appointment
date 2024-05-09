## Book an Appointment

> **Please go through the document carefully to understand the requirement along with our expectations.**

####Scope of work

Enable patient to book an appointment with doctors based on their availability. 

- Initialize: A direct link to initialize data

  - Doctor Data: Add a couple of doctor records. Reference: http://www.doctorsdirectoryindia.com/doctor
  - Appointment Data: Remove all transactional data

- Sign-in (doctor)

- Sections
  
  - Doctor (sign-in required)
    - Upcoming appointment listing with option to filter by date having Today as default date
    
    - Doctor will have option to close (seen patient) or cancel (patient no-show)  appointment, i.e. status will change to 'Closed' or 'Cancelled'
  
  - Reports: Show on the browser (Mandatory), Export to PDF (Optional) 
    - Appointment Summary Report (see Reports section below for more details)
    - Appointment Detailed Report (see Reports section below for more details)
  
- Patient (no login required for patient to book an appointment)
  - Book an Appointment
    - Select a doctor from a list of all the doctors
    - Shows available time-slots for selected doctor
    - Patient to select time-slot (see entity / attribute section), enter name, email, phone
    - Submit to book appointment.
    - After submission the appointment status will be 'Open'

> You are welcome to choose to use any free HTML/CSS template.



####Entities / Attributes

* user
  * user_id
  * name
  * email (use it as username for sign-in)
  * password
* doctor
  * doctor_id
  * doctor_name professional(like neurosurgeon, physician, etc...)
  * user_id(foreign key)
  * appointment_slot_time (15min, 30min, 45min, 60min, etc) - fix for a doctor
  * day_start_time (9:00am, 10:00am, etc)
  * day_end_time (6:00pm, 7:00pm, etc)
* appointment
  * appointment_id
  * appointment_date
  * appointment_time - starting time
  * doctor_id
  * patient_name
  * patinet_email
  * patinet_phone
  * appointment_status (Open, Closed, Cancelled)

horizontal axis - date
vertical axis - time


####Reports

Appointment Summary Report (selected month)

```
Date         # of Appointments   # of Appointment Closed   # of Appointment Cancelled
2020-02-20   5                   4                         1
2020-02-21   5                   5                         0
2020-02-22   5                   2                         0
...
```

Appointment Detailed Report (selected month)

```
Date         Patient Name   Status
2020-02-20   Patient 01     Closed
             Patient 02     Closed
             Patient 03     Closed
             Patient 04     Closed
             Patient 05     Canceled
2020-02-21   Patient 06     Closed
             Patient 07     Closed
             Patient 08     Closed
             Patient 09     Closed
             Patient 10     Closed
2020-02-20   Patient 11     Closed
             Patient 12     Closed
             Patient 13     Open
             Patient 14     Open
             Patient 15     Open
```

> Feel free to format the reports as you find appropriate.



####Technologies / Tools

Ruby, Rails, React / Vue / Angular, MySQL or MongoDB



####Deliverables Expectations

- [x] All features are expected to be working
- [x] Use object oriented approach for the solution with reasonable classes and attributes. Keep it as modular and clean as possible and follow common software practices to include reusability, portability, encapsulation, etc
- [x] Provide code comments and in code documentation where necessary
- [x] Provide sufficient amount of unit tests for implementation (framework of your choice)
- [x] A README describing how to run the program.

Additional notes for candidates taking assignment away from Mindfire office:

- [x] Share github project URL, by pushing both API and UI codes
- [x] Share database schema with sample data as needed.



####Review Process

Submitted code will be reviewed internally to assess the quality of the implementation / code.
Based on review, we may schedule a follow up meeting to do code/design walk through.

