from operator import itemgetter

def main():
    movieInputFile = open("ratings_movieid_dupes.csv", 'r');
    movieOutputFile = open("ratings_movieid_dupes.tsv", 'w');
    extraIntroLines = 0;
    #movieOutputFile.write("Movie\tRelease Date\tRating\tVotes\n");
    bigHugeMovieList = []
    startMovieCount = 0;
    #for each line in file
    for line in movieInputFile:
        line = line.split("\\")[0];
        line = line.strip();
        #if we are past the intro estra header lines, do stuff
        if(len(line) > 0):
            movie = line.split("\t")
            movie[4] = movie[4].replace("\"", "");
            movie.append(movie[4] + movie[5]);
            bigHugeMovieList.append(movie);
            startMovieCount += 1

    bigHugeMovieList = sorted(bigHugeMovieList, key=itemgetter(6))
    movieNum = 0;
    endMovieCount = 0;
    previousName = '';
    previousDate = '';
    currentMovieGroup = []
    for movie in bigHugeMovieList:
        movieNum += 1
        
        if(movie[6] == previousName): # add movie to current group
            currentMovieGroup.append(movie);

        else: # decide most popular movie
            if(len(currentMovieGroup) > 0):
                mostPopular = 0;
                mostPopIndex = 0;
                index = 0;
                for movieInSet in currentMovieGroup:
                    if(mostPopular < int(movieInSet[2])):
                        mostPopular = int(movieInSet[2])
                        mostPopIndex = index;
                    index += 1
                        
                choice = currentMovieGroup[mostPopIndex]
                endMovieCount += 1;
                movieOutputFile.write(choice[0] + "\t" + choice[1] + "\t" +
                                      choice[2] + "\t" + choice[3] +"\t" +
                                      choice[4] + "\t" + choice[5] +"\n");

            currentMovieGroup = [];
            currentMovieGroup.append(movie);
            previousName = movie[6];
        
    print("FINISHED!");
    print("Started With: " + str(startMovieCount));
    print("Ended With: " + str(endMovieCount));
main()

#end
