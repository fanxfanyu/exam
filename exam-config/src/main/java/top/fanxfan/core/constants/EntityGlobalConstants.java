package top.fanxfan.core.constants;

/**
 * @author fanxfan
 */
public class EntityGlobalConstants {

    private EntityGlobalConstants() {
    }

    /**
     * 通用前缀
     */
    public static final String PREFIX = "fanxfan_";

    /**
     * 用户实体名称
     */
    public static final String USER_ENTITY_NAME = PREFIX + "user";

    /**
     * 试题实体名称
     */
    public static final String QUESTION_ENTITY_NAME = PREFIX + "question";

    /**
     * 试题分类实体名称
     */
    public static final String QUESTION_CATALOG_ENTITY_NAME = PREFIX + "question_catalog";

    /**
     * 试题分类与试题关系实体名称
     */
    public static final String QUESTION_CATALOG_RELATION_ENTITY_NAME = PREFIX + "question_catalog_relation";

    /**
     * 试题选项实体名称
     */
    public static final String QUESTION_OPTION_ENTITY_NAME = PREFIX + "question_option";

    /**
     * 考试实体名称
     */
    public static final String EXAM_ENTITY_NAME = PREFIX + "exam";

    /**
     * 考试组成部分实体名称
     */
    public static final String EXAM_PART_ENTITY_NAME = PREFIX + "exam_part";
    /**
     * 试题实体名称
     */
    public static final String EXAM_QUESTION_ENTITY_NAME = PREFIX + "exam_question";


    /**
     * 用户考试实体名称
     */
    public static final String USER_EXAM_ENTITY_NAME = PREFIX + "user_exam";

    /**
     * 用户考试实体名称
     */
    public static final String USER_EXAM_PART_ENTITY_NAME = PREFIX + "user_exam_part";
}
