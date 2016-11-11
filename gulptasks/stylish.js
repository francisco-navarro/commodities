/******* ASSETS TASKS *******/
const args = require('yargs').argv;
const useref = require('gulp-useref');
const lazypipe = require('lazypipe');
const gulpif = require('gulp-if');
const uglify = require('gulp-uglify');
const minifyCss = require('gulp-minify-css');
const del = require('del');
const rev = require('gulp-rev');
const revReplace = require('gulp-rev-replace');
const ngAnnotate = require('gulp-ng-annotate');
const templateCache = require('gulp-angular-templatecache');
const inject = require('gulp-inject');
const wiredep = require('wiredep').stream;
const angularFilesort = require('gulp-angular-filesort');

const baseDir = 'client/app';

module.exports = function(gulp, conf, globOptions) {
    /******* MAIN TASKS *******/

   gulp.task('buildIndex', ['copy-index']);


   gulp.task('clean', ['clean-css', 'clean-js', 'clean-images', 'clean-locales', 'clean-static', 'clean-index', 'clean-jstree-images']);

    /******* INDEX TASKS *******/

   gulp.task('copy-index', ['wiredep-html', 'inject-js', 'inject-css', 'clean-index', 'clean-js', 'clean-css'], function() {
      var assets = useref.assets();

      var task = gulp.src('client/index.html', globOptions)
            .pipe(assets)
            //.pipe(gulpif('**/lib.js', ngAnnotate()))
            .pipe(gulpif('**/combined-min.js', ngAnnotate()));
        // If exists an environment param named --minify then it should minify code

      if (args.minify) {
          var minifyJs = lazypipe()
                .pipe(uglify, {
                   compress: {
                      drop_console: true
                   }
                });

          task = task.pipe(gulpif('**/lib.js', minifyJs()))
                .pipe(gulpif('**/combined-min.js', minifyJs()))
                .pipe(gulpif('*.css', minifyCss()));
       }
      task = task.pipe(rev())
            .pipe(assets.restore())
            .pipe(revReplace())
            .pipe(useref())
            .pipe(gulp.dest('dist/'));

      return task;
   });

   gulp.task('wiredep-html', function() {
      return gulp.src('client/index.html')
            .pipe(wiredep({
               dependencies: true
            }))
            .pipe(gulp.dest('client/'));
   });


   gulp.task('inject-js', [/*'create-templates'*/], function() {
      console.log('Skipping creating templates');
      return gulp.src('client/index.html')
            .pipe(inject(
                gulp.src([baseDir + '/js/**/*.js']).pipe(angularFilesort()).pipe(angularFilesort()), {
                   relative: true
                }
            ))
            .pipe(gulp.dest('client/'));
   });

   gulp.task('inject-css', function() {
      return gulp.src('client/index.html').pipe(inject(gulp.src([baseDir + '/css/**/*.css'], { read: false })))
            .pipe(gulp.dest('client/'));
   });

   gulp.task('create-templates', function() {
      console.log('create templates '+ baseDir + '/html/**/*.html');
      return gulp.src(baseDir + '/html/**/*.html')
        //.pipe(htmlmin({}))
            .pipe(templateCache('app-core-templates.js', {
               root: 'html',
               module: 'app.core',
               standalone: false,
               moduleSystem: 'IIFE'
            }))
            .pipe(uglify())
            .pipe(gulp.dest(baseDir + '/js/core/'));
   });


    /******* CLEAN TASKS *******/

   gulp.task('clean-js', function() {
      del.sync('dist/scripts/*');
   });

   gulp.task('clean-css', function() {
      del.sync('dist/css/*.css');
   });

   gulp.task('clean-index', function() {
      del.sync('dist/index.html');
   });
};
