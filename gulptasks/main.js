module.exports = function(gulp, globOptions) {
    // Sub-tasks
   require('./stylish')(gulp, globOptions);
   gulp.task('build', ['buildIndex']);
};
