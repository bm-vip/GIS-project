function toLocalizingDateString(date, showTime) {
    if (resources.local === "fa") {
        return gregorianStringToShamsiString(date, showTime);
    } else {
        return toGregorianString(date, showTime);
    }
}

function toGregorianString(shamsiString, showTime) {
    if (isNullOrEmpty(shamsiString)) {
        // if (!isNullOrEmpty(showTime) && showTime) {
        //     return "0001-01-01 00:00:00";
        // }
        return null;
    }
    if (resources.local === "fa") {
        return shamsiStringTogregorianString(shamsiString, showTime);
    } else {
        var date = dateSpliter(shamsiString);
        var year = "{0}-{1}-{2}".format(date.year, date.month, date.day);
        if (!isNullOrEmpty(showTime) && showTime) {
            return "{0}T{1}:{2}:{3}".format(
                year,
                date.hour,
                date.minute,
                date.second);
        }
        return year;
    }
}

function shamsiStringTogregorianString(shamsiString, showTime) {
    if (isNullOrEmpty(shamsiString)) return null;
    shamsiString = persianDigitToEnglish(shamsiString);
    var date = dateSpliter(shamsiString);
    var json = toGregorian(parseInt(date.year), parseInt(date.month), parseInt(date.day));
    var year = "{0}-{1}-{2}".format(json.gy, addZero(json.gm), addZero(json.gd));
    if (!isNullOrEmpty(showTime) && showTime) {
        return "{0}T{1}:{2}:{3}".format(
            year,
            addZero(date.hour),
            addZero(date.minute),
            addZero(date.second));
    }
    return year;
}

function addZero(value) {
    if (value < 10)
        return "0" + value;
    return value + "";
}

function gregorianStringToShamsiString(gregorianString, showTime) {
    if (isNullOrEmpty(gregorianString)) return null;
    var date = dateSpliter(gregorianString);
    var json = toJalaali(parseInt(date.year), parseInt(date.month), parseInt(date.day));
    var year = "{0}/{1}/{2}".format(json.jy, json.jm, json.jd);
    if (!isNullOrEmpty(showTime) && showTime) {
        return "{0}T{1}:{2}:{3}".format(
            year,
            date.hour,
            date.minute,
            date.second);
    }
    return year;
}

function dateSpliter(dateString) {
    dateString = dateString.replace('T',' ').replace('Z','');
    if (dateString.indexOf(' ') > -1) {
        dateString = dateString.split(' ');
    } else if (dateString.indexOf('  ') > -1) {
        dateString = dateString.split('  ');
    }
    var spliter = '/';
    if (isArray(dateString)) {
        var year = dateString[0];
        var time = dateString[1];
        if (year.indexOf('-') > -1)
            spliter = '-';
        return {
            year: parseInt(year.split(spliter)[0]),
            month: parseInt(year.split(spliter)[1]),
            day: parseInt(year.split(spliter)[2]),
            hour: parseInt(time.split(':')[0]),
            minute: parseInt(time.split(':')[1]),
            second: parseInt(time.split(':')[2])
        }
    }
    if (dateString.indexOf('-') > -1)
        spliter = '-';
    return {
        year: dateString.split(spliter)[0],
        month: dateString.split(spliter)[1],
        day: dateString.split(spliter)[2],
        hour: 0,
        minute: 0,
        second: 0
    }
}