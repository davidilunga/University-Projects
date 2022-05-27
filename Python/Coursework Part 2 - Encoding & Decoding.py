ALPHABET = 'abcdefghijklmnopqrstuvwxyz'                 #<------- defining the variable alphabet with all the letters in the alpahbet
length = len(ALPHABET)
last = ALPHABET[length-1]
index = 0
start = ALPHABET[0]
end = ALPHABET[25]
new_word = []

def encode():               #<------- This function will start the encoding process
    try:
        word = str(input("Please enter a word that you would like to encode: "))            #<------- Inputting the word that is to be decoded using the alphabet variable
        word_length = len(word) #length is from 0 to length of word - 1
        shift = int(input("Please input a shift value: "))          #<------- Typing a shift will allow Python toknwo how many letters forward the letters need to be shifted
        if shift >=1 and shift <=25:                #<------- Validation to make sure that the shift is between 1 and 25
            for index in range(1):
                for letter in word:                 #<------- 'For' loop allows the program to read through each letter in the word for the length of the word
                    if letter in ALPHABET:          #<-------- then check to see if the letter is in the alphabet
                        old_letter = ALPHABET[ALPHABET.index(letter)]                       #<------- It will mark the position of the initial letter
                        new_letter = ALPHABET[(ALPHABET.index(letter) + shift) % 26]        #<------- This will allow Python to find the letter that is 'shift' times after it, % 26 allows the alphabet to be re-cycled; so instead of finishing at 'z', it will go back to 'a'
                        new_word.append(new_letter)                             #<------ This will add the new shifted letter to a new array called 'new_word'
                        index += 1                                              
                    else:                                                        
                        new_word.append(letter)                         #<------- This will store anything in that isn't in alphabet as it's original letter/symbol/number
            new_word_string = ''.join(str(letter)for letter in new_word)            #<------- this will allow the array to be joined together to make it look like a regular word
            print('The decoded string: "'+str(word)+'".')           
            print('The encoded string "'+new_word_string+'".')
            print('The shift rotation: '+ str(shift)+'.')
        else:
            print("Invalid shift. The shift must be between 1 and 25. ")                #<------- If the shif is <25 or 1>, it will force the program to restart the encode function
            encode()
    except ValueError:
        print("This isn't a valid input")

def decode():               #<------- This function will start the decoding process
    try:
        word = str(input("Please enter a word that you would like to decode: "))            #<------- Inputting the word that is to be decoded using the alphabet variable
        word_length = len(word) #length is from 0 to length of word - 1
        shift = int(input("Please input a shift value: "))          #<------- Typing a shift will allow Python to know how many letters backwards the letters need to be shifted
        if shift >=1 and shift <=25:                #<------- Validation to make sure that the shift is between 1 and 25
            for index in range(1):
                for letter in word:                 #<------- 'For' loop allows the program to read through each letter in the word for the length of the word
                    if letter in ALPHABET:          #<-------- then check to see if the letter is in the alphabet
                        old_letter = ALPHABET[ALPHABET.index(letter)]                       #<------- It will mark the position of the initial letter
                        new_letter = ALPHABET[(ALPHABET.index(letter) - shift) % 26]        #<------- This will allow Python to find the letter that is 'shift' times before it, % 26 allows the alphabet to be re-cycled; so instead of finishing at 'z', it will go back to 'a'
                        new_word.append(new_letter)                             #<------ This will add the new shifted letter to a new array called 'new_word'
                        index += 1                                              
                    else:                                                        
                        new_word.append(letter)                         #<------- This will store anything in that isn't in alphabet as it's original letter/symbol/number
            new_word_string = ''.join(str(letter)for letter in new_word)            #<------- this will allow the array to be joined together to make it look like a regular word
            print('The encoded string: "'+str(word)+'".')           
            print('The decoded string "'+new_word_string+'".')
            print('The shift rotation: '+ str(shift)+'.')
        else:
            print("Invalid shift. The shift must be between 1 and 25. ")                #<------- If the shift is <25 or 1>, it will force the program to restart the encode function
            encode()
    except ValueError:
        print("This isn't a valid input")
    
option = input('Would you like to encode or decode your text? "E" to encode, "D" to decode and "Q" to quit. ')
option = option.lower()
if option == "e":
    encode()
elif option == "d":
    decode()
elif option == "q":
    quit()
elif option != 'e' or 'd' or 'q':
    while True:
        print ('Invalid request. Please input one of the following prompts.')
        option = input('Would you like to encode or decode your text? "E" to encode, "D" to decode and "Q" to quit. ')
        if option == "e":
            encode()
            break
        elif option == "d":
            decode()
            break
        elif option == "q":
            quit()
            break
