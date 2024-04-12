package top.fanxfan.exam.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.fanxfan.exam.entity.Exam;
import top.fanxfan.exam.service.ExamService;
import top.fanxfan.exam.vo.ExamSearch;
import top.fanxfan.exam.vo.ExamVo;

/**
 * 考试Controller
 *
 * @author fanxfan
 */
@RestController
@RequestMapping("/exam")
@Slf4j
@SaCheckLogin
@RequiredArgsConstructor
@Tag(name = "考试Controller", description = "考试管理")
public class ExamController {

    private final ExamService examService;

    /**
     * 获取考试列表
     *
     * @param examSearch 考试搜索条件 {@link ExamSearch}
     * @return 响应结果
     */
    @SaCheckPermission(value = "exam:list", orRole = "super")
    @GetMapping("/list")
    public ResponseEntity<Page<Exam>> list(@Validated ExamSearch examSearch) {
        return ResponseEntity.ok(examService.list(examSearch));
    }

    /**
     * 获取考试详情
     *
     * @param id 考试ID
     * @return 响应结果
     */
    @SaCheckPermission(value = "exam:get", orRole = "super")
    @GetMapping("/{id}")
    public ResponseEntity<Exam> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(examService.get(id));
    }

    /**
     * 删除考试
     *
     * @param id 考试ID
     * @return 响应结果
     */
    @SaCheckPermission(value = "exam:delete", orRole = "super")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(examService.remove(id));
    }

    /**
     * 新增考试
     *
     * @param examVo 考试信息 {@link ExamVo}
     * @return 响应结果
     */

    @SaCheckPermission(value = "exam:add", orRole = "super")
    @PostMapping
    public ResponseEntity<Boolean> add(@Validated @RequestBody ExamVo examVo) {
        return ResponseEntity.ok(examService.createWithAll(examVo));
    }

    /**
     * 分部新增考试
     * <p>仅新增考试信息，即分部创建完整考试</p>
     *
     * @param examVo 考试信息 {@link ExamVo}
     * @return 响应结果
     */
    @SaCheckPermission(value = "exam:only", orRole = "super")
    @PostMapping("/only")
    public ResponseEntity<Exam> addOnly(@Validated @RequestBody ExamVo examVo) {
        return ResponseEntity.ok(examService.createOnlyBase(examVo));
    }

    /**
     * 更新考试
     *
     * @param examVo 考试信息 {@link ExamVo}
     * @return 响应结果
     */
    @SaCheckPermission(value = "exam:update", orRole = "super")
    @PutMapping
    public ResponseEntity<Boolean> update(@Validated @RequestBody ExamVo examVo) {
        return ResponseEntity.ok(examService.updateOnly(examVo));
    }
}
