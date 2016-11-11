const gulp = require('gulp');

const globOptions = {
    nodir: true,
    nosort: true,
    follow: true
};

require('./gulptasks/main')(gulp, globOptions);