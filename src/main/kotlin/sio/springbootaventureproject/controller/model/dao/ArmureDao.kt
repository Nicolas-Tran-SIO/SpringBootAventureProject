package sio.springbootaventureproject.controller.model.dao

import org.springframework.data.jpa.repository.JpaRepository
import sio.springbootaventureproject.controller.model.entity.Armure

interface ArmureDao : JpaRepository<Armure, Long> {
}