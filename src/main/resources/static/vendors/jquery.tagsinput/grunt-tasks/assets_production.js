module.exports = function(grunt) {
   grunt.registerTask('assets:admin',
   [
      'cssmin:plugin',
      'uglify:plugin'
   ]);
};
