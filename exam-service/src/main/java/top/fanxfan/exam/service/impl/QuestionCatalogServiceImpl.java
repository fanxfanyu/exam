package top.fanxfan.exam.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.fanxfan.exam.entity.QQuestionCatalog;
import top.fanxfan.exam.entity.QuestionCatalog;
import top.fanxfan.exam.repository.QuestionCatalogRepository;
import top.fanxfan.exam.service.QuestionCatalogService;
import top.fanxfan.exam.vo.QuestionCatalogVo;

import java.util.List;

/**
 * 试题分类Service实现
 *
 * @author fanxfan
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionCatalogServiceImpl implements QuestionCatalogService {

    private final QuestionCatalogRepository questionCatalogRepository;

    @Override
    public List<QuestionCatalog> tree() {
        Page<QuestionCatalog> all = questionCatalogRepository.findAll(QQuestionCatalog.questionCatalog.parent.isNull(), Pageable.unpaged());
        return all.getContent();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean add(QuestionCatalogVo questionCatalogVo) {
        QuestionCatalog build = QuestionCatalog.builder()
                .name(questionCatalogVo.getName())
                .sequence(questionCatalogVo.getSequence())
                .build();
        if (ObjectUtil.isNotEmpty(questionCatalogVo.getParentId())) {
            questionCatalogRepository.findById(questionCatalogVo.getParentId()).ifPresent(build::setParent);
        }
        questionCatalogRepository.saveAndFlush(build);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean update(QuestionCatalogVo questionCatalogVo) {
        QuestionCatalog questionCatalog = questionCatalogRepository.findById(questionCatalogVo.getId()).orElseThrow(() -> new IllegalArgumentException("分类不存在"));
        BeanUtil.copyProperties(questionCatalogVo, questionCatalog);
        if (ObjectUtil.isNotEmpty(questionCatalogVo.getParentId())) {
            questionCatalogRepository.findById(questionCatalogVo.getParentId()).ifPresent(questionCatalog::setParent);
        }
        questionCatalogRepository.saveAndFlush(questionCatalog);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean remove(Long id) {
        QuestionCatalog questionCatalog = questionCatalogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("分类不存在"));
        questionCatalogRepository.delete(questionCatalog);
        return true;
    }
}
