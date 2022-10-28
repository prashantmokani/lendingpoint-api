package com.prashant.dcms.api.role;

import com.prashant.dcms.api.common.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Audited
public class Role extends BaseEntity {

    private static final long serialVersionUID = 0L;

    @Enumerated(value = EnumType.STRING)
    private RoleName name;

}
