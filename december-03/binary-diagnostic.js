var fs = require('fs');

var file = './december-03/binary-diagnostic.txt';
loadInputFile(file, calculateConsumption)

function verifyLifeSupport(listOfNumbers) {
    //console.log(listOfNumbers);
    //console.log(rawCount);
    var oxygenRatingBinary = calculateRating(listOfNumbers, false);
    var oxygenRating = parseBinaryArray(oxygenRatingBinary);
    var co2ScrubberRatingBinary = calculateRating(listOfNumbers, true);
    var co2ScrubberRating = parseBinaryArray(co2ScrubberRatingBinary);

    console.log(`Part2=${oxygenRating * co2ScrubberRating}`);
}

function calculateRating(listOfNumbers, reverseCount) {
    var filteredList = listOfNumbers;
    for (var i = 0; i < listOfNumbers[0].length; i++) {
        var count = 0;
        filteredList.forEach(element => {
            if (parseInt(element[i]) === 1) {
                count++;
            }
        });
        var bitValue = (count >= filteredList.length / 2) ? 1 : 0;
        if (reverseCount){
            bitValue = (bitValue == 0) ? 1 : 0;
        }
        filteredList = filteredList.filter(element => {
            return parseInt(element[i]) == bitValue;
        });
        if (filteredList.length == 1) {
            filteredList = filteredList[0].map((element) => parseInt(element));
            return filteredList;
        }
    }
    return null;
    
}

function calculateConsumption(listOfNumbers) {
    var gammaRate = [];
    var epsilonRate = [];
    var rawCount = [];
    for (var i = 0; i < listOfNumbers[0].length; i++) {
        rawCount[i] = 0;
        listOfNumbers.forEach(element => {
            rawCount[i] += parseInt(element[i]);
        });
    }
    gammaRate = rawCount.map(element => {
        if (parseInt(element) > 500) {
            return 1;
        } else {
            return 0;
        }
    });
    epsilonRate = rawCount.map(element => {
        if (parseInt(element) < 501) {
            return 1;
        } else {
            return 0;
        }
    });

    var gamma = parseBinaryArray(gammaRate);
    var epsilon = parseBinaryArray(epsilonRate);
    console.log(`Part1=${gamma * epsilon}`);
    verifyLifeSupport(listOfNumbers, rawCount);
}

const parseBinaryArray = array => {
    return array.reduce((previousValue, currentValue) => {
        return (previousValue << 1) | parseInt(currentValue);
    });
};

function loadInputFile(file, callback) {
    fs.readFile(file, 'utf-8', function (err, data) {
        if (err) {
            return console.log(err);
        }
        var array = data.toString().split("\n");
        var listOfNumbers = [];
        array.forEach(element => {
            var binary = element.split("");
            listOfNumbers.push(binary);
        });
        callback(listOfNumbers);
    });
}

