package top.fanxfan.exam.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.fanxfan.exam.entity.Question;
import top.fanxfan.exam.service.QuestionService;
import top.fanxfan.exam.vo.QuestionSearchVo;
import top.fanxfan.exam.vo.QuestionVo;

/**
 * 试题Controller
 *
 * @author fanxfan
 */
@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@SaCheckLogin
@Slf4j
public class QuestionController {

    private final QuestionService questionService;

    /**
     * 获取题目列表
     *
     * @param questionSearchVo 试题搜索
     * @return 响应结果
     */
    @GetMapping("/list")
    @SaCheckPermission(value = "question:list", orRole = "super")
    public ResponseEntity<Page<Question>> list(@Validated QuestionSearchVo questionSearchVo) {
        return ResponseEntity.ok(questionService.list(questionSearchVo));
    }

    /**
     * 新增试题
     *
     * @param questionVo 试题 {@link QuestionVo}
     * @return 响应结果
     */
    @PostMapping
    @SaCheckPermission(value = "question:add", orRole = "super")
    public ResponseEntity<Boolean> add(@RequestBody @Validated QuestionVo questionVo) {
        return ResponseEntity.ok(questionService.add(questionVo));
    }

    /**
     * 删除试题
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission(value = "question:delete", orRole = "super")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(questionService.remove(id));
    }

    /**
     * 修改试题
     *
     * @param questionVo 试题 {@link QuestionVo}
     * @return 响应结果
     */

    @PutMapping
    @SaCheckPermission(value = "question:update", orRole = "super")
    public ResponseEntity<Boolean> update(@RequestBody @Validated QuestionVo questionVo) {
        return ResponseEntity.ok(questionService.update(questionVo));
    }

    /**
     * 获取试题
     *
     * @param id 试题ID
     * @return 响应结果
     */

    @GetMapping("/{id}")
    @SaCheckPermission(value = "question:get", orRole = "super")
    public ResponseEntity<Question> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(questionService.get(id));
    }
}
