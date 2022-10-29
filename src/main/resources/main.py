#! python 3
import sys

ScriptPath = sys.argv[0] if len(sys.argv) > 0 else ""
FileName = sys.argv[1] if len(sys.argv) > 1 else "MyNew.csv"
OutName = sys.argv[2] if len(sys.argv) > 2 else "out.csv"

Slash = "\\"

HeaderToWrite = sys.argv[3] if len(sys.argv) > 3 else "'Followee', 'ID', 'Date', 'UserID', 'Follower', 'Tweets'\n"


def getLastIndex(str, c):
    reversed = str[::-1]

    return len(reversed) - reversed.index(c) - 1


def getFileName():
    index = getLastIndex(ScriptPath, Slash)
    return ScriptPath[0:index:1] + Slash + FileName


def getOutFileName():
    index = getLastIndex(ScriptPath, Slash)
    return ScriptPath[0:index:1] + Slash + OutName


def readInFile(filePath):
    f = open(filePath, "r")
    lines = f.readlines()
    f.close()
    return lines


def writeOutFile(filePath, lines):
    f = open(filePath, "w")

    f.writelines(lines)
    f.close()


def main():
    print("Starting---" + ScriptPath)

    FilePath = getFileName()
    OutPath = getOutFileName()
    print("Reading data in from " + FilePath)

    lines = readInFile(FilePath)

    print("Write data out to " + OutPath)

    lines.insert(0, HeaderToWrite)

    writeOutFile(OutPath, lines)

    print("Completed")


def test():
    for x in sys.argv:
        print(x)

    print("hi from python")

#change this from test to main for real code
test()
# main()
