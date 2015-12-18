package org.gooru.auth.gateway.constants;

/**
 * Created by ashish on 4/12/15.
 */
public class RouteConstants {

  // Helper constants
  public static final String API_VERSION = "v1";
  public static final String API_BASE_ROUTE = "/api/nucleus/" + API_VERSION + "/";
  public static final String API_AUTH_ROUTE = "/api/nucleus/*";

  // Helper: Entity name constants
  public static final String ENTITY_RESOURCES = "resources";
  public static final String ENTITY_QUESTIONS = "questions";
  public static final String ENTITY_ASSESSMENTS = "assessments";
  public static final String ENTITY_COLLECTIONS = "collections";
  public static final String ENTITY_COURSES = "courses";
  public static final String ENTITY_UNITS = "units";
  public static final String ENTITY_LESSONS = "lessons";
  public static final String ENTITY_CLASSES = "classes";
  public static final String ENTITY_COLLABORATORS = "collaborators";
  public static final String ENTITY_TAXONOMY = "taxonomy";
  public static final String ENTITY_AUTHENTICATION = "authentication";
  public static final String ENTITY_TX_SUBJECT = "subjects";
  public static final String ENTITY_TX_COURSE = "courses";
  public static final String ENTITY_TX_DOMAIN = "domain";

  // Helper: Entity Id constants
  public static final String ID_RESOURCE = "resourceId";
  public static final String ID_QUESTION = "questionId";
  public static final String ID_ASSESSMENT = "assessmentId";
  public static final String ID_COLLECTION = "collectionId";
  public static final String ID_COURSE = "courseId";
  public static final String ID_UNIT = "unitId";
  public static final String ID_LESSON = "lessonId";
  public static final String ID_CLASS = "classId";
  public static final String ID_SEQUENCE = "sequence";
  public static final String ID_TX_SUBJECT = "subjectId";
  public static final String ID_TX_COURSE = "courseId";

  // Helper: Operations
  public static final String OP_ORDER = "order";
  public static final String OP_LOOKUP = "lookups";
  public static final String OP_MEMBER = "members";
  public static final String OP_INVITE = "invites";

  // Misc helpers
  public static final String RES_EDUCATIONAL_USE = "educational-use";
  public static final String RES_DOK = "dok";
  public static final String RES_ACCESS_HAZARDS = "access-hazards";
  public static final String RES_READING_LEVELS = "reading-levels";
  public static final String RES_ADS = "ads";
  public static final String RES_MEDIA_FEATURES = "media-features";
  public static final String RES_CEN21SKILLS = "21-century-skills";
  public static final String RES_GRADES = "grades";
  public static final String SEP = "/";
  public static final String COLON = ":";

  // Actual End Point Constants: Note that constant values may be duplicated but
  // we are going to have individual constant values to work with for each
  // point instead of reusing the same

  // Get Resource By Id = /api/nucleus/{version}/resources/{resource-id}
  public static final String EP_RESOURCE_GET = API_BASE_ROUTE + ENTITY_RESOURCES + SEP + COLON + ID_RESOURCE;

  // Create a new Resource = /api/nucleus/{version}/resources
  public static final String EP_RESOURCE_CREATE = API_BASE_ROUTE + ENTITY_RESOURCES;

  // Update an existing Resource =
  // /api/nucleus/{version}/resources/{resource-id}
  public static final String EP_RESOURCE_UPDATE = API_BASE_ROUTE + ENTITY_RESOURCES + SEP + COLON + ID_RESOURCE;

  // Get Question By Id = /api/nucleus/{version}/questions/{question-id}
  public static final String EP_QUESTION_GET = API_BASE_ROUTE + ENTITY_QUESTIONS + SEP + COLON + ID_QUESTION;

  // Create a new Question = /api/nucleus/{version}/questions
  public static final String EP_QUESTION_CREATE = API_BASE_ROUTE + ENTITY_QUESTIONS;

  // Update an existing Question =
  // /api/nucleus/{version}/questions/{question-id}
  public static final String EP_QUESTION_UPDATE = API_BASE_ROUTE + ENTITY_QUESTIONS + SEP + COLON + ID_QUESTION;

  // Get an Assessment by Id =
  // /api/nucleus/{version}/assessments/{assessment-id}
  public static final String EP_ASSESSMENT_GET = API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT;

  // Create a New Assessment = /api/nucleus/{version}/assessments
  public static final String EP_ASSESSMENT_CREATE = API_BASE_ROUTE + ENTITY_ASSESSMENTS;

  // Update an Existing Assessment =
  // /api/nucleus/{version}/assessments/{assessment-id}
  public static final String EP_ASSESSMENT_UPDATE = API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT;

  // Delete an Assessment = /api/nucleus/{version}/assessments/{assessment-id}
  public static final String EP_ASSESSMENT_DELETE = API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT;

  // Add a New Question to Assessment =
  // /api/nucleus/{version}/assessments/{assessment-id}/questions
  public static final String EP_ASSESSMENT_ADD_QUESTION = API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT + SEP + ENTITY_QUESTIONS;

  // Remove a Question from Assessment =
  // /api/nucleus/{version}/assessments/{assessment-id}/questions/{question-id}
  public static final String EP_ASSESSMENT_REMOVE_QUESTION =
          API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT + SEP + ENTITY_QUESTIONS + SEP + COLON + ID_QUESTION;

  // Copy an Existing Question to Assessment =
  // /api/nucleus/{version}/assessments/{assessment-id}/questions
  public static final String EP_ASSESSMENT_COPY_QUESTION =
          API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT + SEP + ENTITY_ASSESSMENTS;

  // Reorder Questions in Assessments =
  // /api/nucleus/{version}/assessments/{assessment-id}/questions/order
  public static final String EP_ASSESSMENT_QUESTION_REORDER =
          API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT + SEP + ENTITY_QUESTIONS + SEP + OP_ORDER;

  // Get the List of Collaborators for an Assessment =
  // /api/nucleus/{version}/assessments/{assessment-id}/collaborators
  public static final String EP_ASSESSMENT_COLLABORATORS_GET =
          API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT + SEP + ENTITY_COLLABORATORS;

  // Update the List of Collaborators for an Assessment =
  // /api/nucleus/{version}/assessments/{assessment-id}/collaborators
  public static final String EP_ASSESSMENT_COLLABORATORS_UPDATE =
          API_BASE_ROUTE + ENTITY_ASSESSMENTS + SEP + COLON + ID_ASSESSMENT + SEP + ENTITY_COLLABORATORS;

  // Get a Collection based on Id =
  // /api/nucleus/{version}/collections/{collection-id}
  public static final String EP_COLLECTION_GET = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION;

  // Create a New Collection = /api/nucleus/{version}/collections
  public static final String EP_COLLECTION_CREATE = API_BASE_ROUTE + ENTITY_COLLECTIONS;

  // Update an Existing Collection =
  // /api/nucleus/{version}/collections/{collection-id}
  public static final String EP_COLLECTION_UPDATE = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION;

  // Delete a Collection = /api/nucleus/{version}/collections/{collection-id}
  public static final String EP_COLLECTION_DELETE = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION;

  // Add a New Question to Collection =
  // /api/nucleus/{version}/collections/{collection-id}/questions
  public static final String EP_COLLECTION_QUESTION_ADD = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + ENTITY_QUESTIONS;

  // Remove a Question from Collection =
  // /api/nucleus/{version}/collections/{collection-id}/questions/{question-id}
  public static final String EP_COLLECTION_QUESTION_REMOVE =
          API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + ENTITY_QUESTIONS + SEP + COLON + ID_QUESTION;

  // Remove a Resource from a Collection =
  // /api/nucleus/{version}/collections/{collection-id}/resources/{resource-id}/{sequence-id}
  public static final String EP_COLLECTION_RESOURCE_REMOVE =
          API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + ENTITY_RESOURCES + SEP + COLON + ID_RESOURCE;

  // Copy an Existing Question to Collection =
  // /api/nucleus/{version}/collections/{collection-id}/questions
  public static final String EP_COLLECTION_QUESTION_COPY = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + ENTITY_QUESTIONS;

  // Add a New Resource to Collection =
  // /api/nucleus/{version}/collections/{collection-id}/resources
  public static final String EP_COLLECTION_RESOURCE_ADD = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + ENTITY_RESOURCES;

  // Add an Existing Resource to Collection =
  // /api/nucleus/{version}/collections/{collection-id}/resources
  public static final String EP_COLLECTION_RESOURCE_COPY = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + ENTITY_RESOURCES;

  // Reorder Resources/Questions in Existing Collection =
  // /api/nucleus/{version}/collections/{collection-id}/order
  public static final String EP_COLLECTION_CONTENT_REORDER = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + OP_ORDER;

  // Update Existing Resource in a Collection =
  // /api/nucleus/{version}/collections/{collection-id}/resources/{resource-id}/{sequence-id}
  public static final String EP_COLLECTION_RESOURCE_UPDATE = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP
          + ENTITY_RESOURCES + SEP + COLON + ID_RESOURCE + SEP + COLON + ID_SEQUENCE;

  // Update Existing Question in a Collection =
  // /api/nucleus/{version}/collections/{collection-id}/questions/{question-id}/{sequence-id}
  public static final String EP_COLLECTION_QUESTION_UPDATE = API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP
          + ENTITY_QUESTIONS + SEP + COLON + ID_QUESTION + SEP + COLON + ID_SEQUENCE;

  // Get the List of Collaborators for a Collection =
  // /api/nucleus/{version}/collections/{collection-id}/collaborators
  public static final String EP_COLLECTION_COLLABORATORS_GET =
          API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + ENTITY_COLLABORATORS;

  // Update the List of Collaborators for a Collection =
  // /api/nucleus/{version}/collections/{collection-id}/collaborators
  public static final String EP_COLLECTION_COLLABORATORS_UPDATE =
          API_BASE_ROUTE + ENTITY_COLLECTIONS + SEP + COLON + ID_COLLECTION + SEP + ENTITY_COLLABORATORS;

  // Create a New Course = /api/nucleus/{version}/courses
  public static final String EP_COURSE_CREATE = API_BASE_ROUTE + ENTITY_COURSES;

  // Copy a Course = /api/nucleus/{version}/courses
  public static final String EP_COURSE_COPY = API_BASE_ROUTE + ENTITY_COURSES;

  // Update Existing Course = /api/nucleus/{version}/courses/{course-id}
  public static final String EP_COURSE_UPDATE = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE;

  // Fetch a Course by Id = /api/nucleus/{version}/courses/{course-id}
  public static final String EP_COURSE_GET = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE;

  // List Courses for the specified User Context =
  // /api/nucleus/{version}/courses
  // TODO:

  // Delete Course = /api/nucleus/{version}/courses/{course-id}
  public static final String EP_COURSE_DELETE = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE;

  // Reorder Units in Existing Course =
  // /api/nucleus/{version}/courses/{course-id}/order
  public static final String EP_COURSE_CONTENT_REORDER = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + OP_ORDER;

  // Get the List of Collaborators for a Course =
  // /api/nucleus/{version}/course/{course-id}/collaborators
  public static final String EP_COURSE_COLLABORATOR_GET = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_COLLABORATORS;

  // Update the List of Collaborators for a Course =
  // /api/nucleus/{version}/courses/{course-id}/collaborators
  public static final String EP_COURSE_COLLABORATOR_UPDATE = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_COLLABORATORS;

  // Create a new Unit = /api/nucleus/{version}/courses/{course-id}/units
  public static final String EP_UNIT_CREATE = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS;

  // Copy an Existing Unit to Existing Course =
  // /api/nucleus/{version}/courses/{course-id}/units/
  public static final String EP_UNIT_COPY = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS;

  // Update Existing Unit =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}
  public static final String EP_UNIT_UPDATE = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT;

  // Fetch a Unit by Id =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}
  public static final String EP_UNIT_GET = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT;

  // Delete Unit = /api/nucleus/{version}/courses/{course-id}/units/{unit-id}
  public static final String EP_UNIT_DELETE = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT;

  // Reorder Lessons in Existing Unit =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}/order/
  public static final String EP_UNIT_CONTENT_REORDER =
          API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT + SEP + OP_ORDER;

  // Create a new Lesson =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}/lessons
  public static final String EP_LESSON_CREATE =
          API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT + SEP + ENTITY_LESSONS;

  // Copy an Existing Lesson to Existing Unit =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}/lessons
  public static final String EP_LESSON_COPY =
          API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT + SEP + ENTITY_LESSONS;

  // Update Existing Lesson =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}/lessons/{lesson-id}
  public static final String EP_LESSON_UPDATE = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT
          + SEP + ENTITY_LESSONS + SEP + COLON + ID_LESSON;

  // Fetch a Lesson by Id =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}/lessons/{lesson-id}
  public static final String EP_LESSON_GET = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT
          + SEP + ENTITY_LESSONS + SEP + COLON + ID_LESSON;

  // Delete Lesson =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}/lessons/{lesson-id}
  public static final String EP_LESSON_DELETE = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON + ID_UNIT
          + SEP + ENTITY_LESSONS + SEP + COLON + ID_LESSON;

  // Reorder Collections/Assessments in Existing Lesson =
  // /api/nucleus/{version}/courses/{course-id}/units/{unit-id}/lessons/{lesson-id}/order/
  public static final String EP_LESSON_CONTENT_REORDER = API_BASE_ROUTE + ENTITY_COURSES + SEP + COLON + ID_COURSE + SEP + ENTITY_UNITS + SEP + COLON
          + ID_UNIT + SEP + ENTITY_LESSONS + SEP + COLON + ID_LESSON + SEP + OP_ORDER;

  // Create a class = /api/nucleus/{version}/classes
  public static final String EP_CLASS_CREATE = API_BASE_ROUTE + ENTITY_CLASSES;

  // Update a Class = /api/nucleus/{version}/classes/{class-id}
  public static final String EP_CLASS_UPDATE = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS;

  // Get the List of Collaborators for a Class =
  // /api/nucleus/{version}/classes/{class-id}/collaborators
  public static final String EP_CLASS_COLLABORATOR_GET = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + ENTITY_COLLABORATORS;

  // Update the List of Collaborators for a Class =
  // /api/nucleus/{version}/classes/{class-id}/collaborators
  public static final String EP_CLASS_COLLABORATOR_UPDATE = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + ENTITY_COLLABORATORS;

  // Join a Class = /api/nucleus/{version}/classes/{class-id}/members
  public static final String EP_CLASS_JOIN = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + OP_MEMBER;

  // List members who have joined a Class =
  // /api/nucleus/{version}/classes/{class-id}/members
  public static final String EP_CLASS_MEMBERS_GET = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + OP_MEMBER;

  // Invite to a Class = /api/nucleus/{version}/classes/{class-id}/invites
  public static final String EP_CLASS_JOIN_INVITE = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + OP_INVITE;

  // List pending members for a Class =
  // /api/nucleus/{version}/classes/{class-id}/invites
  public static final String EP_CLASS_MEMBERS_PENDING_GET = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + OP_INVITE;

  // Associate a Course with the Class =
  // /api/nucleus/{version}/classes/{class-id}/courses
  public static final String EP_CLASS_COURSE_ATTACH = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + ENTITY_COURSES;

  // Fetch All Archived Classes - for User =
  // /api/nucleus/{version}/classes?status=archived
  public static final String EP_CLASS_ARCHIVED_LIST = API_BASE_ROUTE + ENTITY_CLASSES;

  // Fetch All Active Classes - for User =
  // /api/nucleus/{version}/classes?status=active
  public static final String EP_CLASS_LIST = API_BASE_ROUTE + ENTITY_CLASSES;

  // Fetch All Classes which are not associated with Course for User context =
  // /api/nucleus/{version}/classes?course-assigned=false
  public static final String EP_CLASS_WITHOUT_COURSE = API_BASE_ROUTE + ENTITY_CLASSES;

  // Fetch All Classes for which the specified Content is not Visible =
  // /api/nucleus/{version}/classes?content-id={content-id}
  public static final String EP_CLASSES_CONTENT_IS_INVISIBLE = API_BASE_ROUTE + ENTITY_CLASSES;

  // Publish a specific Collection/Assessment to a multiple Classes =
  // /api/nucleus/{version}/classes
  public static final String EP_CLASSES_CONTENT_PUBLISH = API_BASE_ROUTE + ENTITY_CLASSES;

  // Fetch All Classes assigned to a specific Course =
  // /api/nucleus/{version}/classes?assigned-course={course-id}
  public static final String EP_CLASSES_FOR_COURSE = API_BASE_ROUTE + ENTITY_CLASSES;

  // Fetch published status for content of a Course associated with a specific
  // Class = /api/nucleus/{version}/classes/{class-id}/courses/{course-id}
  public static final String EP_CLASS_COURSE_CONTENT_VISIBILITY =
          API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + SEP + ENTITY_COURSES + SEP + COLON + ID_COURSE;

  // Publish content of a Course to a specific Class =
  // /api/nucleus/{version}/classes/{class-id}/courses/{course-id}
  public static final String EP_CLASS_CONTENT_PUBLISH = API_BASE_ROUTE + ENTITY_CLASSES + SEP + COLON + ID_CLASS + ENTITY_COURSES + COLON + ID_COURSE;

  // Retrieve a specific Class by the Class Code =
  // /api/nucleus/{version}/classes?class-code={class-code}
  public static final String EP_CLASS_GET = API_BASE_ROUTE + ENTITY_CLASSES;

  // Authenticate = /api/nucleus/{version}/authentication
  public static final String EP_AUTHENTICATE = API_BASE_ROUTE + ENTITY_AUTHENTICATION;

  // List Subjects in Taxonomy = /api/nucleus/{version}/taxonomy/subjects
  public static final String EP_SUBJECTS_LIST = API_BASE_ROUTE + ENTITY_TAXONOMY + SEP + ENTITY_TX_SUBJECT;

  // List Courses in Taxonomy =
  // /api/nucleus/{version}/taxonomy/subjects/{subject-id}/courses
  public static final String EP_COURSES_LIST_BY_SUBJECT =
          API_BASE_ROUTE + ENTITY_TAXONOMY + SEP + ENTITY_TX_SUBJECT + SEP + COLON + ID_TX_SUBJECT + SEP + ENTITY_TX_COURSE;

  // List Domains in Taxonomy =
  // /api/nucleus/{version}/taxonomy/subjects/{subject-id}/courses/{course-id}/domains
  public static final String EP_DOMAINS_LIST_BY_COURSE = API_BASE_ROUTE + ENTITY_TAXONOMY + SEP + ENTITY_TX_SUBJECT + SEP + COLON + ID_TX_SUBJECT
          + SEP + ENTITY_TX_COURSE + COLON + ID_TX_COURSE + SEP + ENTITY_TX_DOMAIN;

  // List Standards for Domains in Taxonomy =
  // /api/nucleus/{version}/taxonomy/subjects/{subject-id}/courses/{course-id}/domains/{domain-id}/standards/{standards-framework-id}
  public static final String EP_STANDARDS_BY_DOMAINS = API_BASE_ROUTE;

  // List Standards Frameworks = /api/nucleus/{version}/taxonomy/standards
  public static final String EP_STANDARDS_FRAMEWORK_LIST = API_BASE_ROUTE;

  /*
   * TODO: standards and learning targets
   *
   * // List Standards Framework Level 1 =
   * /api/nucleus/{version}/taxonomy/standards/{standards-framework-id}/level1
   * public static final String EP_
   * 
   * // List Standards Framework Level 2 =
   * /api/nucleus/{version}/taxonomy/standards/{standards-framework-id}/level1/{
   * level1-id}/level2 public static final String EP_
   * 
   * // List Standards Framework Level 3 =
   * /api/nucleus/{version}/taxonomy/standards/{standards-framework-id}/level1/{
   * level1-id}/level2/{level2-id}/level3 public static final String EP_
   * 
   * // List Standards Framework Level 4 =
   * /api/nucleus/{version}/taxonomy/standards/{standards-framework-id}/level1/{
   * level1-id}/level2/{level2-id}/level3/{level3-id}/level4 public static final
   * String EP_
   * 
   */
  // List Depth of Knowledge = /api/nucleus/{version}/taxonomy/dok
  public static final String EP_DOK_LIST = API_BASE_ROUTE + ENTITY_TAXONOMY + RES_DOK;

  // List Educational Use = /api/nucleus/{version}/lookups/educational-use
  public static final String EP_EDUCATIONAL_USE_LIST = API_BASE_ROUTE + OP_LOOKUP + RES_EDUCATIONAL_USE;

  // List Access Hazards = /api/nucleus/{version}/lookups/access-hazards
  public static final String EP_ACCESS_HAZARDS_LIST = API_BASE_ROUTE + OP_LOOKUP + SEP + RES_ACCESS_HAZARDS;

  // List Reading Levels = /api/nucleus/{version}/lookups/reading-levels
  public static final String EP_READING_LEVELS_LIST = API_BASE_ROUTE + OP_LOOKUP + SEP + RES_READING_LEVELS;

  // List Advertisement Status in Content = /api/nucleus/{version}/lookups/ads
  public static final String EP_AD_STATUS_LIST = API_BASE_ROUTE + OP_LOOKUP + RES_ADS;

  // List Media Features = /api/nucleus/{version}/lookups/media-features
  public static final String EP_MEDIA_FEATURES_LIST = API_BASE_ROUTE + OP_LOOKUP + SEP + RES_MEDIA_FEATURES;

  // List 21st Century Skills =
  // /api/nucleus/{version}/taxonomy/21-century-skills
  public static final String EP_21_CENTURY_SKILLS_LIST = API_BASE_ROUTE + ENTITY_TAXONOMY + SEP + RES_CEN21SKILLS;

  // List Grades = /api/nucleus/{version}/taxonomy/grades
  public static final String EP_GRADES_LIST = API_BASE_ROUTE + ENTITY_TAXONOMY + SEP + RES_GRADES;

}
