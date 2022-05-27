no_of_students=0
bracket_1 = 0               #Variabeles are set and then later used inn the program
bracket_2 = 0
bracket_3 = 0
bracket_4 = 0
mark = 0
number_2 = 0
over_40 = 0
lowest = 100
highest =0
maximum = 0
counter = 0

if mark>=0:                     
    while True:             #<--------- The loop starts if the mark is greater than or equal to 0 and is checked with and 'if' statement.
        try:
            while mark>=0 and mark <=100:
                mark = int(input("Enter the mark or a negative number to exit: "))     #<------- This is the input quetstion for the mark allowing the user to enter an answer      
                no_of_students +=1
                number_2 = number_2 + mark
                if mark >=0 and mark <30:           # <------------    The next 4 'if' and 'elif' statments allows the program to find out what category the mark would go into
                    bracket_1 += 1                  # <------- Category 1
                    if mark < lowest:
                        lowest = mark
                    elif mark >=0 and mark > highest:   # <-------- This nested if statement will also check if it is it highest or the lowest mark that has been entered
                        highest = mark
                elif mark >=30 and mark <40:        # <------- Category 2
                    bracket_2 += 1
                    if mark < lowest:
                        lowest = mark
                    elif mark >=0 and mark > highest:
                        highest = mark
                elif mark >=40 and mark <70:            # <------- Category 3
                    bracket_3 += 1
                    over_40 += 1
                    if mark < lowest:
                        lowest = mark
                    elif mark >=0 and mark > highest:
                        highest = mark
                elif mark >=70 and mark <=100:           # <------- Category 4
                    bracket_4 += 1
                    over_40 += 1
                    if mark < lowest:
                        lowest = mark
                    elif mark >=0 and mark > highest:
                        highest = mark
                if bracket_1 > maximum and bracket_1 > bracket_2 and bracket_1 > bracket_3 and bracket_1 > bracket_4:       #<---------- This is for Part 2: Vertical Histogram to find out the maximum number of rows that is needed to print the vertical histogram
                    maximum = bracket_1
                elif bracket_2 > maximum and bracket_2 > bracket_1 and bracket_2 > bracket_3 and bracket_2 > bracket_4:
                    maximum = bracket_2
                elif bracket_3 > maximum and bracket_3 > bracket_1 and bracket_3 > bracket_2 and bracket_3 > bracket_4:
                    maximum = bracket_3
                elif bracket_4 > maximum and bracket_4 > bracket_1 and bracket_4 > bracket_2 and bracket_4 > bracket_3:
                    maximum = bracket_4
                if mark > 100:                              # <------- This tells the program what to do if mark is over 100 (still in the while loop)
                    no_of_students -= 1
                    number_2 = number_2 - mark
                    True
                    while True:                             # <------- Starts a new loop to check if the marks entered are still above 100
                        number_2 = number_2 + mark
                        no_of_students += 1
                        try:
                            if mark > 100:
                                number_2 = number_2 - mark              # <------- If the number is above 100, it will take away 1 student that is added as well as the mark entered.
                                no_of_students -= 1
                                mark = int(input("Invalid mark. Number is greater than 100.\nPlease enter a valid mark: "))
                                False                                   # <------- Resets the loop by making it False
                            elif mark < 100:                    # <------- If the number after is below 100, it will go back into the regular loop.
                                number_2 = number_2 + mark
                                if mark >=0 and mark <30:
                                    bracket_1 += 1
                                elif mark >=30 and mark <40:
                                    bracket_2 += 1
                                elif mark >=40 and mark <70:
                                    bracket_3 += 1
                                    over_40 += 1
                                elif mark >=70 and mark <100:
                                    bracket_4 += 1
                                    over_40 += 1
                                if mark < lowest:
                                    lowest = mark
                                elif mark >=0 and mark > highest:
                                    highest = mark
                                break
                            else:
                                break
                        except ValueError:                          # <------- Validation to check if a number between 0 - 100 is inputted
                            print("Invalid input. The mark must be between 0 and 100. ")
            break
        except ValueError:                              # <------- Validation to check that an integer has been inputted and not a string
            print("This isn't a valid input.")

if mark < 0:                                # <------- What happens when the mark is any number less than 0
    no_of_students -=1
    number_2 -=mark
    for students in range(1):

        print("\n0 - 29: ", end='')         
        for y in range(bracket_1):              # <------- This will print the number of students that got a mark within each bracket.
            print(' *',end='')               # <------- The "end=''" allows the '*' to be joined together, side by side
        print('')
        print("\n30 - 39: ", end='')
        for y in range(bracket_2):
            print(' *',end='')
        print('')
        print("\n40 - 69: ", end='')
        for y in range(bracket_3):
            print(' *',end ='')
        print('')
        print("\n70 - 100: ", end='')
        for y in range(bracket_4):
            print(' *',end='')        
    print('')
    print ("\nThe lowest mark was", lowest)             # <------- This will print the lowest mark given by a student
    print ("The highest mark was", highest)             # <------- This will print the highest mark given by a student
    average = 0
    while True:
        try:
            average = number_2/(no_of_students)
            print("The average mark is",round(average,3),"to 3 decimal places")      #<------- This will calculate and print the average mark given by the total number of students.
            break                                                                   # It will also print it to 3 decimal places
        except ZeroDivisionError:                   #<------- To avoid an error, it will display the average is 0 if there was no value inputted
            print("The average is 0")
            break
    print(no_of_students,"student's marks were recorded.")              #<------- This will print the total number of students
    print(over_40, "people got over 40 marks.")                     #<------- This will print the total number of students that scored over 40 marks

print('')
print("Part D: Vertical Histogram")
print('')

if mark < 0:
    no_of_students -=1
    number_2 -=mark
    print("  0 - 29 |", end='')
    print("30 - 39|", end='')
    print("40 - 69|", end='')
    print("70 - 100 ",)
    for i in range(maximum):                    #<------- This will identify the maximum number of rows that the histogram needs for the vertical histogram
        counter += 1                        #<------- The counter is what checks to see in there should be any stars under each category.                
        if bracket_1 >= counter:
            print ('    *    ', end = '')               #<------- This will check each star in each bracket individually; to see if there is a value
        else:
            print ('         ', end = '')               # <---------If the value is higher than the counter, it will show a '*' and if not, it will stay blank

        if bracket_2 >= counter:
            print ('    *    ', end = '')
        else:
            print ('         ', end = '')

        if bracket_3 >= counter:
            print ('    *    ', end = '')
        else:
            print ('         ', end = '')

        if bracket_4 >= counter:
            print ('    *    ', end = '')
        else:
            print ('         ', end = '')

        print('\n')
