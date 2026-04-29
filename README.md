# Conference-Vij
Conference for APCSA


Project Description:
The goal behind conference planner is to create a program that can take in a raw data file containing informatino
about the number of tables, maximum number of people sitting at a table, and the maximum number of people allowed to attend a
conference per company to develop a seating arrangement that ensure that no two individuals of the same company are seated at 
the same table. Additionally, the user must be able to manually add individual attendees to the pre-registered participants. The 
provided data file has 16 companies with a maximum of 10 attendees per company and 10 tables with a seating capcity of 10 attendees
each. 

Goals for functionality (from Assignment Description Document):

- Create functionality (methods) to
- Bulk load from file, the pre-registered people
- Enroll or register a person manually
- Check user amounts (10/tablemax, 100 max total, etc)
- Place users at table
- Print 'rosters' by table
- Print rosters by company
- Add getter functionality to 'find' a person and report what table they are at


Approach:
To approach this problem, I implemented an algorithm that first goes through and assigns all attendees that can be assigned
in the limits of the constraints (one attendee per company at a table, table size, number of tables, etc.). Then the program checks to see if the maximum attendees per company rules has been broken among the attendees and reflects that in the finalized seating chart. Then, the user has the option to view the tables, print out a specifc table's roster, print out a specific company's roster, search for a specific attendee, or view unassigned registrants.

Limitations:
The main limitation to this project origniate from the given constraint (which are inputted by the user). Depending on the number of tables,
maximum number of individuals or one company per table, and seats per table, the feasbility of the conference is impacted and the
number of unassigned registrants varies accordingly.
