package com.newcrud

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PlayerController {

    PlayerService playerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond playerService.list(params), model:[playerCount: playerService.count()]
    }

    def show(Long id) {
        respond playerService.get(id)
    }

    def create() {
        respond new Player(params)
    }

    def save(Player player) {
        if (player == null) {
            notFound()
            return
        }

        try {
            playerService.save(player)
        } catch (ValidationException e) {
            respond player.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'player.label', default: 'Player'), player.id])
                redirect player
            }
            '*' { respond player, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond playerService.get(id)
    }

    def update(Player player) {
        if (player == null) {
            notFound()
            return
        }

        try {
            playerService.save(player)
        } catch (ValidationException e) {
            respond player.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'player.label', default: 'Player'), player.id])
                redirect player
            }
            '*'{ respond player, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        playerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'player.label', default: 'Player'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'player.label', default: 'Player'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
