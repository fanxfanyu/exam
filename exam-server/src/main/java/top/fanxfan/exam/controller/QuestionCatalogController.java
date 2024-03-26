package top.fanxfan.exam.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.fanxfan.exam.entity.QuestionCatalog;
import top.fanxfan.exam.service.QuestionCatalogService;
import top.fanxfan.exam.vo.QuestionCatalogVo;

import java.util.List;

/**
 * 试题分类Controller
 *
 * @author fanxfan
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/question/catalog")
@Slf4j
public class QuestionCatalogController {

    private final QuestionCatalogService questionCatalogService;

    /**
     * 获取试题分类树
     *
     * @return 试题分类树
     */
    @GetMapping("/tree")
    @SaCheckPermission(value = "question:catalog:tree", orRole = "super")
    public ResponseEntity<List<QuestionCatalog>> tree() {
        return ResponseEntity.ok(questionCatalogService.tree());
    }

    /**
     * 新增试题分类
     *
     * @param questionCatalogVo 试题分类 {@link  QuestionCatalogVo}
     * @return 试题分类
     */
    @PostMapping
    @SaCheckPermission(value = "question:catalog:add", orRole = "super")
    public ResponseEntity<Boolean> add(@RequestBody @Validated QuestionCatalogVo questionCatalogVo) {
        return ResponseEntity.ok(questionCatalogService.add(questionCatalogVo));
    }

    /**
     * 修改试题分类
     *
     * @param questionCatalogVo 试题分类 {@link  QuestionCatalogVo}
     * @return 响应结果
     */
    @PutMapping
    @SaCheckPermission(value = "question:catalog:update", orRole = "super")
    public ResponseEntity<Boolean> update(@RequestBody @Validated QuestionCatalogVo questionCatalogVo) {
        return ResponseEntity.ok(questionCatalogService.update(questionCatalogVo));
    }

    /**
     * 删除试题分类
     *
     * @param id 试题分类id
     * @return 响应结果
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission(value = "question:catalog:delete", orRole = "super")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(questionCatalogService.remove(id));
    }
}
