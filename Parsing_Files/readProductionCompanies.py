import re
import math
def main():
    companyFile = open("production-companies.list", 'r');
    ratingsFile = open("ratings.list", 'r');
    genresFile = open("genres.list", 'r');

    companyOutFile = open("production-companies.tsv", 'w');
    ratingsOutFile = open("ratings.tsv", 'w');
    genresOutFile = open("genres.tsv", 'w');
    
    companyOutFile.write("Movie\tRelease Date\tProduction Campany\n");
    companyOutFile.write("Movie\tRelease Date\tProduction Campany\n");
    companyOutFile.write("Movie\tRelease Date\tProduction Campany\n");

    #Constants
    meanCompanyCount = 1.441;
    companyIntroLines = 14;

    #Variables
    lineNum = 0;
    movieCount = 0;
    stopAfterLine = 0;#set to 0 to do whole file
    name = "";
    previousName = "";
    pc = "";
    previousPC = "";
    isEpisode = False;
    studiosForMovie = 1;
    maxNameLength = 0;
    maxStudios = 0;
    totalStudioCount = 0;
    weirdestDate = 3000;
    #for each line in file
    for line in companyFile:
        
        lineNum += 1
        
        #if we are past the intro estra header lines, do stuff
        if(lineNum > companyIntroLines and len(line) > 0):
            name =  re.search("[^{}\[\]\)]* \(", line)
            date = re.search("\([\d?][\d?][\d?][\d?]\)", line)
            pc = re.search("\t[^\t{}]*[\n\]]", line)
            #print(repr(line));
            if "{" not in line: 
                isEpisode = False;
            else:
                isEpisode = True;
            if(name and date and pc):
                movieName = name.group(0)
                movieName = movieName[0:len(movieName)-2]
                #print("MovieName: " + movieName)# for testing crap
                movieDate = date.group(0).replace("(", "").replace(")", "")
                if(movieDate == "????"):
                    movieDate = 0000;
                #print("MovieDATE: " + movieDate)# for testing crap
                moviePC = pc.group(0).strip()
                if((not isEpisode) and len(movieName) < 200 and len(moviePC) < 200):
                    companyOutFile.write(movieName + "\t" + str(movieDate) + "\t" + moviePC + "\n");
                    #companyOutFile.write(movieName.replace(",", "") + "," + movieDate + "," + moviePC + "\n");
                    if(movieName == previousName):
                        studiosForMovie += 1
                    else:
                        if(len(movieName) > maxNameLength):
                            maxNameLength = len(movieName);

                        if((weirdestDate > int(movieDate)) and int(movieDate) != 0):
                            weirdestDate = int(movieDate);
                            thatline = lineNum
                            longestName = movieName
                            longestDate = movieDate
                            longestPCName = moviePC

                        movieCount += 1;
                        totalStudioCount += (studiosForMovie - meanCompanyCount) * (studiosForMovie - meanCompanyCount);
                        if(studiosForMovie > maxStudios):
                            maxStudios = studiosForMovie;
                        studiosForMovie = 1;
                    #print(movieName) 
                    #print(movieDate) 
                    #print(moviePC)
                    previousName = movieName
                    
        if(lineNum == stopAfterLine):
            print("FINISHED!\nMOVIE COUNT: " + str(movieCount));
            print("mean: " + str(meanCompanyCount) + " sd: " + str(round(math.sqrt(float(totalStudioCount)/float(movieCount)), 3)));
            print("MAX STUDIOS: " + str(maxStudios));
            print("Max PC Name Length: " + str(maxNameLength));
            print("NAME: " + str(longestName));
            print("DATE: " + str(longestDate));
            print("PC NAME: " + str(longestPCName));
            print("Weirdest Date: " + str(weirdestDate) + " LINE: " + str(thatline));
            return
    print("FINISHED!\nMOVIE COUNT: " + str(movieCount));
    print("mean: " + str(meanCompanyCount) + " sd: " + str(round(math.sqrt(float(totalStudioCount)/float(movieCount)), 3)));
    print("MAX STUDIOS: " + str(maxStudios));
    print("Max PC Name Length: " + str(maxNameLength));
    print("NAME: " + str(longestName));
    print("DATE: " + str(longestDate));
    print("PC NAME: " + str(longestPCName));
    print("Weirdest Date: " + str(weirdestDate) + " LINE: " + str(thatline));

    #close files
    companyFile.close();
    ratingsFile.close();
    genresFile.close();
    companyOutFile.close();
    ratingsOutFile.close();
    genresOutFile.close();
main()

#end

##FINISHED!
##MOVIE COUNT: 98375
##mean: 1.441 sd: 1.104360134872765
##MAX STUDIOS: 54
