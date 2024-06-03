# Import writer class from csv module
from csv import writer
import sys

# List that we want to add as a new row
i = str(sys.argv[1])
list = i.split(',')

# Open our existing CSV file in append mode
# Create a file object for this file
with open('C:\\Users\\ASUS\\git\\WebThoiTrang\\WebThoiTranng\\src\\main\\java\\python\\rating_raw.csv', 'a') as f_object:

	# Pass this file object to csv.writer()
	# and get a writer object
	writer_object = writer(f_object)

	# Pass the list as an argument intos
	# the writerow()
	writer_object.writerow(list)
	# Close the file object
	f_object.close()


print(list)
