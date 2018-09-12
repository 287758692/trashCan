/*!
 * bootstrap-fileinput v4.4.2
 * http://plugins.krajee.com/file-input
 *
 * Author: Kartik Visweswaran
 * Copyright: 2014 - 2017, Kartik Visweswaran, Krajee.com
 *
 * Licensed under the BSD 3-Clause
 * https://github.com/kartik-v/bootstrap-fileinput/blob/master/LICENSE.md
 */
!function (e) {
    "use strict";
    "function" == typeof define && define.amd ? define(["jquery"], e) : "object" == typeof module && module.exports ? module.exports = e(require("jquery")) : e(window.jQuery)
}(function (e) {
    "use strict";
    e.fn.fileinputLocales = {}, e.fn.fileinputThemes = {}, String.prototype.setTokens = function (e) {
        var i, t, a = this.toString();
        for (i in e) e.hasOwnProperty(i) && (t = new RegExp("{" + i + "}", "g"), a = a.replace(t, e[i]));
        return a
    };
    var i, t;
    i = {
        FRAMES: ".kv-preview-thumb",
        SORT_CSS: "file-sortable",
        STYLE_SETTING: 'style="width:{width};height:{height};"',
        OBJECT_PARAMS: '<param name="controller" value="true" />\n<param name="allowFullScreen" value="true" />\n<param name="allowScriptAccess" value="always" />\n<param name="autoPlay" value="false" />\n<param name="autoStart" value="false" />\n<param name="quality" value="high" />\n',
        DEFAULT_PREVIEW: '<div class="file-preview-other">\n<span class="{previewFileIconClass}">{previewFileIcon}</span>\n</div>',
        MODAL_ID: "kvFileinputModal",
        MODAL_EVENTS: ["show", "shown", "hide", "hidden", "loaded"],
        objUrl: window.URL || window.webkitURL,
        compare: function (e, i, t) {
            return void 0 !== e && (t ? e === i : e.match(i))
        },
        isIE: function (e) {
            if ("Microsoft Internet Explorer" !== navigator.appName) return !1;
            if (10 === e) return new RegExp("msie\\s" + e, "i").test(navigator.userAgent);
            var i, t = document.createElement("div");
            return t.innerHTML = "<!--[if IE " + e + "]> <i></i> <![endif]-->", i = t.getElementsByTagName("i").length, document.body.appendChild(t), t.parentNode.removeChild(t), i
        },
        initModal: function (i) {
            var t = e("body");
            t.length && i.appendTo(t)
        },
        isEmpty: function (i, t) {
            return void 0 === i || null === i || 0 === i.length || t && "" === e.trim(i)
        },
        isArray: function (e) {
            return Array.isArray(e) || "[object Array]" === Object.prototype.toString.call(e)
        },
        ifSet: function (e, i, t) {
            return t = t || "", i && "object" == typeof i && e in i ? i[e] : t
        },
        cleanArray: function (e) {
            return e instanceof Array || (e = []), e.filter(function (e) {
                return void 0 !== e && null !== e
            })
        },
        spliceArray: function (e, i) {
            var t, a = 0, n = [];
            if (!(e instanceof Array)) return [];
            for (t = 0; t < e.length; t++) t !== i && (n[a] = e[t], a++);
            return n
        },
        getNum: function (e, i) {
            return i = i || 0, "number" == typeof e ? e : ("string" == typeof e && (e = parseFloat(e)), isNaN(e) ? i : e)
        },
        hasFileAPISupport: function () {
            return !(!window.File || !window.FileReader)
        },
        hasDragDropSupport: function () {
            var e = document.createElement("div");
            return !i.isIE(9) && (void 0 !== e.draggable || void 0 !== e.ondragstart && void 0 !== e.ondrop)
        },
        hasFileUploadSupport: function () {
            return i.hasFileAPISupport() && window.FormData
        },
        hasBlobSupport: function () {
            try {
                return !!window.Blob && Boolean(new Blob)
            } catch (e) {
                return !1
            }
        },
        hasArrayBufferViewSupport: function () {
            try {
                return 100 === new Blob([new Uint8Array(100)]).size
            } catch (e) {
                return !1
            }
        },
        dataURI2Blob: function (e) {
            var t, a, n, r, o, l,
                s = window.BlobBuilder || window.WebKitBlobBuilder || window.MozBlobBuilder || window.MSBlobBuilder,
                d = i.hasBlobSupport(), c = (d || s) && window.atob && window.ArrayBuffer && window.Uint8Array;
            if (!c) return null;
            for (t = e.split(",")[0].indexOf("base64") >= 0 ? atob(e.split(",")[1]) : decodeURIComponent(e.split(",")[1]), a = new ArrayBuffer(t.length), n = new Uint8Array(a), r = 0; r < t.length; r += 1) n[r] = t.charCodeAt(r);
            return o = e.split(",")[0].split(":")[1].split(";")[0], d ? new Blob([i.hasArrayBufferViewSupport() ? n : a], {type: o}) : (l = new s, l.append(a), l.getBlob(o))
        },
        addCss: function (e, i) {
            e.removeClass(i).addClass(i)
        },
        getElement: function (t, a, n) {
            return i.isEmpty(t) || i.isEmpty(t[a]) ? n : e(t[a])
        },
        uniqId: function () {
            return Math.round((new Date).getTime() + 100 * Math.random())
        },
        htmlEncode: function (e) {
            return e.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&apos;")
        },
        replaceTags: function (i, t) {
            var a = i;
            return t ? (e.each(t, function (e, i) {
                "function" == typeof i && (i = i()), a = a.split(e).join(i)
            }), a) : a
        },
        cleanMemory: function (e) {
            var t = e.is("img") ? e.attr("src") : e.find("source").attr("src");
            i.objUrl.revokeObjectURL(t)
        },
        findFileName: function (e) {
            var i = e.lastIndexOf("/");
            return -1 === i && (i = e.lastIndexOf("\\")), e.split(e.substring(i, i + 1)).pop()
        },
        checkFullScreen: function () {
            return document.fullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || document.msFullscreenElement
        },
        toggleFullScreen: function (e) {
            var t = document, a = t.documentElement;
            a && e && !i.checkFullScreen() ? a.requestFullscreen ? a.requestFullscreen() : a.msRequestFullscreen ? a.msRequestFullscreen() : a.mozRequestFullScreen ? a.mozRequestFullScreen() : a.webkitRequestFullscreen && a.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT) : t.exitFullscreen ? t.exitFullscreen() : t.msExitFullscreen ? t.msExitFullscreen() : t.mozCancelFullScreen ? t.mozCancelFullScreen() : t.webkitExitFullscreen && t.webkitExitFullscreen()
        },
        moveArray: function (e, i, t) {
            if (t >= e.length) for (var a = t - e.length; a-- + 1;) e.push(void 0);
            return e.splice(t, 0, e.splice(i, 1)[0]), e
        },
        cleanZoomCache: function (e) {
            var i = e.closest(".kv-zoom-cache-theme");
            i.length || (i = e.closest(".kv-zoom-cache")), i.remove()
        },
        setOrientation: function (e, i) {
            var t, a, n, r = new DataView(e), o = 0, l = 1;
            if (!(65496 !== r.getUint16(o) || e.length < 2)) {
                for (o += 2, t = r.byteLength; t - 2 > o;) switch (a = r.getUint16(o), o += 2, a) {
                    case 65505:
                        n = r.getUint16(o), t = n - o, o += 2;
                        break;
                    case 274:
                        l = r.getUint16(o + 6, !1), t = 0
                }
                i && i(l)
            }
        },
        validateOrientation: function (e, t) {
            if (window.FileReader && window.DataView) {
                var a, n = new FileReader;
                n.onloadend = function () {
                    a = n.result, i.setOrientation(a, t)
                }, n.readAsArrayBuffer(e)
            }
        },
        adjustOrientedImage: function (e, i) {
            var t, a, n;
            if (e.hasClass("is-portrait-gt4")) {
                if (i) return void e.css({width: e.parent().height()});
                e.css({
                    height: "auto",
                    width: e.height()
                }), t = e.parent().offset().top, a = e.offset().top, n = t - a, e.css("margin-top", n)
            }
        }
    }, t = function (t, a) {
        var n = this;
        n.$element = e(t), n._validate() && (n.isPreviewable = i.hasFileAPISupport(), n.isIE9 = i.isIE(9), n.isIE10 = i.isIE(10), n.isPreviewable || n.isIE9 ? (n._init(a), n._listen()) : n.$element.removeClass("file-loading"))
    }, t.prototype = {
        constructor: t, _cleanup: function () {
            var e = this;
            e.reader = null, e.formdata = {}, e.uploadCount = 0, e.uploadStatus = {}, e.uploadLog = [], e.uploadAsyncCount = 0, e.loadedImages = [], e.totalImagesCount = 0, e.ajaxRequests = [], e.clearStack(), e.fileInputCleared = !1, e.fileBatchCompleted = !0, e.isPreviewable || (e.showPreview = !1), e.isError = !1, e.ajaxAborted = !1, e.cancelling = !1
        }, _init: function (t) {
            var a, n, r, o = this, l = o.$element;
            o.options = t, e.each(t, function (e, t) {
                switch (e) {
                    case"minFileCount":
                    case"maxFileCount":
                    case"minFileSize":
                    case"maxFileSize":
                    case"maxFilePreviewSize":
                    case"resizeImageQuality":
                    case"resizeIfSizeMoreThan":
                    case"progressUploadThreshold":
                    case"initialPreviewCount":
                    case"zoomModalHeight":
                    case"minImageHeight":
                    case"maxImageHeight":
                    case"minImageWidth":
                    case"maxImageWidth":
                        o[e] = i.getNum(t);
                        break;
                    default:
                        o[e] = t
                }
            }), o.rtl && (r = o.previewZoomButtonIcons.prev, o.previewZoomButtonIcons.prev = o.previewZoomButtonIcons.next, o.previewZoomButtonIcons.next = r), o._cleanup(), o.$form = l.closest("form"), o._initTemplateDefaults(), o.uploadFileAttr = i.isEmpty(l.attr("name")) ? "file_data" : l.attr("name"), n = o._getLayoutTemplate("progress"), o.progressTemplate = n.replace("{class}", o.progressClass), o.progressCompleteTemplate = n.replace("{class}", o.progressCompleteClass), o.progressErrorTemplate = n.replace("{class}", o.progressErrorClass), o.dropZoneEnabled = i.hasDragDropSupport() && o.dropZoneEnabled, o.isDisabled = l.attr("disabled") || l.attr("readonly"), o.isDisabled && l.attr("disabled", !0), o.isUploadable = i.hasFileUploadSupport() && !i.isEmpty(o.uploadUrl), o.isClickable = o.browseOnZoneClick && o.showPreview && (o.isUploadable && o.dropZoneEnabled || !i.isEmpty(o.defaultPreviewContent)), o.slug = "function" == typeof t.slugCallback ? t.slugCallback : o._slugDefault, o.mainTemplate = o.showCaption ? o._getLayoutTemplate("main1") : o._getLayoutTemplate("main2"), o.captionTemplate = o._getLayoutTemplate("caption"), o.previewGenericTemplate = o._getPreviewTemplate("generic"), o.resizeImage && (o.maxImageWidth || o.maxImageHeight) && (o.imageCanvas = document.createElement("canvas"), o.imageCanvasContext = o.imageCanvas.getContext("2d")), i.isEmpty(l.attr("id")) && l.attr("id", i.uniqId()), o.namespace = ".fileinput_" + l.attr("id").replace(/-/g, "_"), void 0 === o.$container ? o.$container = o._createContainer() : o._refreshContainer(), a = o.$container, o.$dropZone = a.find(".file-drop-zone"), o.$progress = a.find(".kv-upload-progress"), o.$btnUpload = a.find(".fileinput-upload"), o.$captionContainer = i.getElement(t, "elCaptionContainer", a.find(".file-caption")), o.$caption = i.getElement(t, "elCaptionText", a.find(".file-caption-name")), o.$previewContainer = i.getElement(t, "elPreviewContainer", a.find(".file-preview")), o.$preview = i.getElement(t, "elPreviewImage", a.find(".file-preview-thumbnails")), o.$previewStatus = i.getElement(t, "elPreviewStatus", a.find(".file-preview-status")), o.$errorContainer = i.getElement(t, "elErrorContainer", o.$previewContainer.find(".kv-fileinput-error")), i.isEmpty(o.msgErrorClass) || i.addCss(o.$errorContainer, o.msgErrorClass), o.$errorContainer.hide(), o.previewInitId = "preview-" + i.uniqId(), o._initPreviewCache(), o._initPreview(!0), o._initPreviewActions(), o._setFileDropZoneTitle(), l.removeClass("file-loading"), l.attr("disabled") && o.disable(), o._initZoom(), o.hideThumbnailContent && i.addCss(o.$preview, "hide-content")
        }, _initTemplateDefaults: function () {
            var t, a, n, r, o, l, s, d, c, u, p, f, m, v, h, g, w, _, b, C, y, T, E, x, F, S, I, k, P, z, A, $, D, U, B,
                j, L = this;
            t = '{preview}\n<div class="kv-upload-progress hide"></div>\n<div class="input-group {class}">\n   {caption}\n   <div class="input-group-btn">\n       {remove}\n       {cancel}\n       {upload}\n       {browse}\n   </div>\n</div>', a = '{preview}\n<div class="kv-upload-progress hide"></div>\n{remove}\n{cancel}\n{upload}\n{browse}\n', n = '<div class="file-preview {class}">\n    {close}    <div class="{dropClass}">\n    <div class="file-preview-thumbnails">\n    </div>\n    <div class="clearfix"></div>    <div class="file-preview-status text-center text-success"></div>\n    <div class="kv-fileinput-error"></div>\n    </div>\n</div>', o = '<div class="close fileinput-remove">&times;</div>\n', r = '<i class="glyphicon glyphicon-file kv-caption-icon"></i>', l = '<div tabindex="500" class="form-control file-caption {class}">\n   <div class="file-caption-name"></div>\n</div>\n', s = '<button type="{type}" tabindex="500" title="{title}" class="{css}" {status}>{icon} {label}</button>', d = '<a href="{href}" tabindex="500" title="{title}" class="{css}" {status}>{icon} {label}</a>', c = '<div tabindex="500" class="{css}" {status}>{icon} {label}</div>', u = '<div id="' + i.MODAL_ID + '" class="file-zoom-dialog modal fade" tabindex="-1" aria-labelledby="' + i.MODAL_ID + 'Label"></div>', p = '<div class="modal-dialog modal-lg{rtl}" role="document">\n  <div class="modal-content">\n    <div class="modal-header">\n      <div class="kv-zoom-actions pull-right">{toggleheader}{fullscreen}{borderless}{close}</div>\n      <h3 class="modal-title">{heading} <small><span class="kv-zoom-title"></span></small></h3>\n    </div>\n    <div class="modal-body">\n      <div class="floating-buttons"></div>\n      <div class="kv-zoom-body file-zoom-content {zoomFrameClass}"></div>\n{prev} {next}\n    </div>\n  </div>\n</div>\n', f = '<div class="progress">\n    <div class="{class}" role="progressbar" aria-valuenow="{percent}" aria-valuemin="0" aria-valuemax="100" style="width:{percent}%;">\n        {status}\n     </div>\n</div>', m = " <samp>({sizeText})</samp>", v = '<div class="file-thumbnail-footer">\n    <div class="file-footer-caption" title="{caption}">{caption}<br>{size}</div>\n    {progress} {indicator} {actions}\n</div>', h = '{drag}\n<div class="file-actions">\n    <div class="file-footer-buttons">\n        {upload} {delete} {zoom} {other}    </div>\n    <div class="clearfix"></div>\n</div>', g = '<button type="button" class="kv-file-remove {removeClass}" title="{removeTitle}" {dataUrl}{dataKey}>{removeIcon}</button>\n', w = '<button type="button" class="kv-file-upload {uploadClass}" title="{uploadTitle}">{uploadIcon}</button>', _ = '<button type="button" class="kv-file-zoom {zoomClass}" title="{zoomTitle}">{zoomIcon}</button>', b = '<span class="file-drag-handle {dragClass}" title="{dragTitle}">{dragIcon}</span>', C = '<div class="file-upload-indicator" title="{indicatorTitle}">{indicator}</div>', y = '<div class="file-preview-frame {frameClass}" id="{previewId}" data-fileindex="{fileindex}" data-template="{template}"', T = y + '><div class="kv-file-content">\n', E = y + ' title="{caption}"><div class="kv-file-content">\n', x = "</div>{footer}\n</div>\n", F = "{content}\n", S = '<div class="kv-preview-data file-preview-html" title="{caption}" ' + i.STYLE_SETTING + ">{data}</div>\n", I = '<img src="{data}" class="file-preview-image kv-preview-data" title="{caption}" alt="{caption}" ' + i.STYLE_SETTING + ">\n", k = '<textarea class="kv-preview-data file-preview-text" title="{caption}" readonly ' + i.STYLE_SETTING + ">{data}</textarea>\n", P = '<video class="kv-preview-data file-preview-video" width="{width}" height="{height}" controls>\n<source src="{data}" type="{type}">\n' + i.DEFAULT_PREVIEW + "\n</video>\n", z = '<div class="file-preview-audio"><audio class="kv-preview-data" controls>\n<source src="{data}" type="{type}">\n' + i.DEFAULT_PREVIEW + "\n</audio></div>\n", A = '<object class="kv-preview-data file-object" type="application/x-shockwave-flash" width="{width}" height="{height}" data="{data}">\n' + i.OBJECT_PARAMS + " " + i.DEFAULT_PREVIEW + "\n</object>\n", $ = '<object class="kv-preview-data file-object {typeCss}" data="{data}" type="{type}" width="{width}" height="{height}">\n<param name="movie" value="{caption}" />\n' + i.OBJECT_PARAMS + " " + i.DEFAULT_PREVIEW + "\n</object>\n", D = '<embed class="kv-preview-data" src="{data}" width="{width}" height="{height}" type="application/pdf">\n', U = '<div class="kv-preview-data file-preview-other-frame">\n' + i.DEFAULT_PREVIEW + "\n</div>\n", B = '<div class="kv-zoom-cache" style="display:none">{zoomContent}</div>', j = {
                width: "100%",
                height: "100%",
                "min-height": "480px"
            }, L.defaults = {
                layoutTemplates: {
                    main1: t,
                    main2: a,
                    preview: n,
                    close: o,
                    fileIcon: r,
                    caption: l,
                    modalMain: u,
                    modal: p,
                    progress: f,
                    size: m,
                    footer: v,
                    indicator: C,
                    actions: h,
                    actionDelete: g,
                    actionUpload: w,
                    actionZoom: _,
                    actionDrag: b,
                    btnDefault: s,
                    btnLink: d,
                    btnBrowse: c,
                    zoomCache: B
                },
                previewMarkupTags: {tagBefore1: T, tagBefore2: E, tagAfter: x},
                previewContentTemplates: {
                    generic: F,
                    html: S,
                    image: I,
                    text: k,
                    video: P,
                    audio: z,
                    flash: A,
                    object: $,
                    pdf: D,
                    other: U
                },
                allowedPreviewTypes: ["image", "html", "text", "video", "audio", "flash", "pdf", "object"],
                previewTemplates: {},
                previewSettings: {
                    image: {width: "auto", height: "160px"},
                    html: {width: "213px", height: "160px"},
                    text: {width: "213px", height: "160px"},
                    video: {width: "auto", height: "100%", "max-width": "100%"},
                    audio: {width: "100%", height: "30px"},
                    flash: {width: "auto", height: "100%", "max-width": "100%"},
                    object: {height: "100%"},
                    pdf: {width: "160px", height: "160px"},
                    other: {width: "160px", height: "160px"}
                },
                previewZoomSettings: {
                    image: {width: "auto", height: "auto", "max-width": "100%", "max-height": "100%"},
                    html: j,
                    text: j,
                    video: {width: "auto", height: "100%", "max-width": "100%"},
                    audio: {width: "100%", height: "30px"},
                    flash: {width: "auto", height: "480px"},
                    object: {width: "auto", height: "100%", "max-width": "100%", "min-height": "480px"},
                    pdf: j,
                    other: {width: "auto", height: "100%", "min-height": "480px"}
                },
                fileTypeSettings: {
                    image: function (e, t) {
                        return i.compare(e, "image.*") || i.compare(t, /\.(gif|png|jpe?g)$/i)
                    }, html: function (e, t) {
                        return i.compare(e, "text/html") || i.compare(t, /\.(htm|html)$/i)
                    }, text: function (e, t) {
                        return i.compare(e, "text.*") || i.compare(t, /\.(xml|javascript)$/i) || i.compare(t, /\.(txt|md|csv|nfo|ini|json|php|js|css)$/i)
                    }, video: function (e, t) {
                        return i.compare(e, "video.*") && (i.compare(e, /(ogg|mp4|mp?g|mov|webm|3gp)$/i) || i.compare(t, /\.(og?|mp4|webm|mp?g|mov|3gp)$/i))
                    }, audio: function (e, t) {
                        return i.compare(e, "audio.*") && (i.compare(t, /(ogg|mp3|mp?g|wav)$/i) || i.compare(t, /\.(og?|mp3|mp?g|wav)$/i))
                    }, flash: function (e, t) {
                        return i.compare(e, "application/x-shockwave-flash", !0) || i.compare(t, /\.(swf)$/i)
                    }, pdf: function (e, t) {
                        return i.compare(e, "application/pdf", !0) || i.compare(t, /\.(pdf)$/i)
                    }, object: function () {
                        return !0
                    }, other: function () {
                        return !0
                    }
                },
                fileActionSettings: {
                    showRemove: !0,
                    showUpload: !0,
                    showZoom: !0,
                    showDrag: !0,
                    removeIcon: '<i class="glyphicon glyphicon-trash text-danger"></i>',
                    removeClass: "btn btn-xs btn-default",
                    removeTitle: "Remove file",
                    uploadIcon: '<i class="glyphicon glyphicon-upload text-info"></i>',
                    uploadClass: "btn btn-xs btn-default",
                    uploadTitle: "Upload file",
                    zoomIcon: '<i class="glyphicon glyphicon-zoom-in"></i>',
                    zoomClass: "btn btn-xs btn-default",
                    zoomTitle: "View Details",
                    dragIcon: '<i class="glyphicon glyphicon-menu-hamburger"></i>',
                    dragClass: "text-info",
                    dragTitle: "Move / Rearrange",
                    dragSettings: {},
                    indicatorNew: '<i class="glyphicon glyphicon-hand-down text-warning"></i>',
                    indicatorSuccess: '<i class="glyphicon glyphicon-ok-sign text-success"></i>',
                    indicatorError: '<i class="glyphicon glyphicon-exclamation-sign text-danger"></i>',
                    indicatorLoading: '<i class="glyphicon glyphicon-hand-up text-muted"></i>',
                    indicatorNewTitle: "Not uploaded yet",
                    indicatorSuccessTitle: "Uploaded",
                    indicatorErrorTitle: "Upload Error",
                    indicatorLoadingTitle: "Uploading ..."
                }
            }, e.each(L.defaults, function (i, t) {
                return "allowedPreviewTypes" === i ? void(void 0 === L.allowedPreviewTypes && (L.allowedPreviewTypes = t)) : void(L[i] = e.extend(!0, {}, t, L[i]))
            }), L._initPreviewTemplates()
        }, _initPreviewTemplates: function () {
            var t, a = this, n = a.defaults, r = a.previewMarkupTags, o = r.tagAfter;
            e.each(n.previewContentTemplates, function (e, n) {
                i.isEmpty(a.previewTemplates[e]) && (t = r.tagBefore2, "generic" !== e && "image" !== e && "html" !== e && "text" !== e || (t = r.tagBefore1), a.previewTemplates[e] = t + n + o)
            })
        }, _initPreviewCache: function () {
            var t = this;
            t.previewCache = {
                data: {}, init: function () {
                    var e = t.initialPreview;
                    e.length > 0 && !i.isArray(e) && (e = e.split(t.initialPreviewDelimiter)), t.previewCache.data = {
                        content: e,
                        config: t.initialPreviewConfig,
                        tags: t.initialPreviewThumbTags
                    }
                }, fetch: function () {
                    return t.previewCache.data.content.filter(function (e) {
                        return null !== e
                    })
                }, count: function (e) {
                    return t.previewCache.data && t.previewCache.data.content ? e ? t.previewCache.data.content.length : t.previewCache.fetch().length : 0
                }, get: function (a, n) {
                    var r, o, l, s, d, c, u, p = "init_" + a, f = t.previewCache.data, m = f.config[a],
                        v = f.content[a], h = t.previewInitId + "-" + p,
                        g = i.ifSet("previewAsData", m, t.initialPreviewAsData),
                        w = function (e, a, n, r, o, l, s, d, c) {
                            return d = " file-preview-initial " + i.SORT_CSS + (d ? " " + d : ""), t._generatePreviewTemplate(e, a, n, r, o, !1, null, d, l, s, c)
                        };
                    return v ? (n = void 0 === n ? !0 : n, l = i.ifSet("type", m, t.initialPreviewFileType || "generic"), d = i.ifSet("filename", m, i.ifSet("caption", m)), c = i.ifSet("filetype", m, l), s = t.previewCache.footer(a, n, m && m.size || null), u = i.ifSet("frameClass", m), r = g ? w(l, v, d, c, h, s, p, u) : w("generic", v, d, c, h, s, p, u, l).setTokens({content: f.content[a]}), f.tags.length && f.tags[a] && (r = i.replaceTags(r, f.tags[a])), i.isEmpty(m) || i.isEmpty(m.frameAttr) || (o = e(document.createElement("div")).html(r), o.find(".file-preview-initial").attr(m.frameAttr), r = o.html(), o.remove()), r) : ""
                }, add: function (e, a, n, r) {
                    var o, l = t.previewCache.data;
                    return i.isArray(e) || (e = e.split(t.initialPreviewDelimiter)), r ? (o = l.content.push(e) - 1, l.config[o] = a, l.tags[o] = n) : (o = e.length - 1, l.content = e, l.config = a, l.tags = n), t.previewCache.data = l, o
                }, set: function (e, a, n, r) {
                    var o, l, s = t.previewCache.data;
                    if (e && e.length && (i.isArray(e) || (e = e.split(t.initialPreviewDelimiter)), l = e.filter(function (e) {
                        return null !== e
                    }), l.length)) {
                        if (void 0 === s.content && (s.content = []), void 0 === s.config && (s.config = []), void 0 === s.tags && (s.tags = []), r) {
                            for (o = 0; o < e.length; o++) e[o] && s.content.push(e[o]);
                            for (o = 0; o < a.length; o++) a[o] && s.config.push(a[o]);
                            for (o = 0; o < n.length; o++) n[o] && s.tags.push(n[o])
                        } else s.content = e, s.config = a, s.tags = n;
                        t.previewCache.data = s
                    }
                }, unset: function (e) {
                    var i = t.previewCache.count();
                    if (i) {
                        if (1 === i) return t.previewCache.data.content = [], t.previewCache.data.config = [], t.previewCache.data.tags = [], t.initialPreview = [], t.initialPreviewConfig = [], void(t.initialPreviewThumbTags = []);
                        t.previewCache.data.content[e] = null, t.previewCache.data.config[e] = null, t.previewCache.data.tags[e] = null
                    }
                }, out: function () {
                    var e, i, a = "", n = t.previewCache.count(!0);
                    if (0 === n) return {content: "", caption: ""};
                    for (i = 0; n > i; i++) a += t.previewCache.get(i);
                    return e = t._getMsgSelected(t.previewCache.count()), {content: a, caption: e}
                }, footer: function (e, a, n) {
                    var r = t.previewCache.data;
                    if (!r || !r.config || 0 === r.config.length || i.isEmpty(r.config[e])) return "";
                    a = void 0 === a ? !0 : a;
                    var o, l = r.config[e], s = i.ifSet("caption", l), d = i.ifSet("width", l, "auto"),
                        c = i.ifSet("url", l, !1), u = i.ifSet("key", l, null), p = t.fileActionSettings,
                        f = t.initialPreviewShowDelete || !1, m = i.ifSet("showDelete", l, i.ifSet("showDelete", p, f)),
                        v = i.ifSet("showZoom", l, i.ifSet("showZoom", p, !0)),
                        h = i.ifSet("showDrag", l, i.ifSet("showDrag", p, !0)), g = c === !1 && a;
                    return o = t._renderFileActions(!1, m, v, h, g, c, u, !0), t._getLayoutTemplate("footer").setTokens({
                        progress: t._renderThumbProgress(),
                        actions: o,
                        caption: s,
                        size: t._getSize(n),
                        width: d,
                        indicator: ""
                    })
                }
            }, t.previewCache.init()
        }, _handler: function (e, i, t) {
            var a = this, n = a.namespace, r = i.split(" ").join(n + " ") + n;
            e && e.length && e.off(r).on(r, t)
        }, _log: function (e) {
            var i = this, t = i.$element.attr("id");
            t && (e = '"' + t + '": ' + e), "undefined" != typeof window.console.log ? window.console.log(e) : window.alert(e)
        }, _validate: function () {
            var e = this, i = "file" === e.$element.attr("type");
            return i || e._log('The input "type" must be set to "file" for initializing the "bootstrap-fileinput" plugin.'), i
        }, _errorsExist: function () {
            var i, t = this;
            return t.$errorContainer.find("li").length ? !0 : (i = e(document.createElement("div")).html(t.$errorContainer.html()), i.find("span.kv-error-close").remove(), i.find("ul").remove(), !!e.trim(i.text()).length)
        }, _errorHandler: function (e, i) {
            var t = this, a = e.target.error, n = function (e) {
                t._showError(e.replace("{name}", i))
            };
            n(a.code === a.NOT_FOUND_ERR ? t.msgFileNotFound : a.code === a.SECURITY_ERR ? t.msgFileSecured : a.code === a.NOT_READABLE_ERR ? t.msgFileNotReadable : a.code === a.ABORT_ERR ? t.msgFilePreviewAborted : t.msgFilePreviewError)
        }, _addError: function (e) {
            var i = this, t = i.$errorContainer;
            e && t.length && (t.html(i.errorCloseButton + e), i._handler(t.find(".kv-error-close"), "click", function () {
                t.fadeOut("slow")
            }))
        }, _resetErrors: function (e) {
            var i = this, t = i.$errorContainer;
            i.isError = !1, i.$container.removeClass("has-error"), t.html(""), e ? t.fadeOut("slow") : t.hide()
        }, _showFolderError: function (e) {
            var t, a = this, n = a.$errorContainer;
            e && (t = a.msgFoldersNotAllowed.replace("{n}", e), a._addError(t), i.addCss(a.$container, "has-error"), n.fadeIn(800), a._raise("filefoldererror", [e, t]))
        }, _showUploadError: function (e, t, a) {
            var n = this, r = n.$errorContainer, o = a || "fileuploaderror",
                l = t && t.id ? '<li data-file-id="' + t.id + '">' + e + "</li>" : "<li>" + e + "</li>";
            return 0 === r.find("ul").length ? n._addError("<ul>" + l + "</ul>") : r.find("ul").append(l), r.fadeIn(800), n._raise(o, [t, e]), n.$container.removeClass("file-input-new"), i.addCss(n.$container, "has-error"), !0
        }, _showError: function (e, t, a) {
            var n = this, r = n.$errorContainer, o = a || "fileerror";
            return t = t || {}, t.reader = n.reader, n._addError(e), r.fadeIn(800), n._raise(o, [t, e]), n.isUploadable || n._clearFileInput(), n.$container.removeClass("file-input-new"), i.addCss(n.$container, "has-error"), n.$btnUpload.attr("disabled", !0), !0
        }, _noFilesError: function (e) {
            var t = this, a = t.minFileCount > 1 ? t.filePlural : t.fileSingle,
                n = t.msgFilesTooLess.replace("{n}", t.minFileCount).replace("{files}", a), r = t.$errorContainer;
            t._addError(n), t.isError = !0, t._updateFileDetails(0), r.fadeIn(800), t._raise("fileerror", [e, n]), t._clearFileInput(), i.addCss(t.$container, "has-error")
        }, _parseError: function (i, t, a, n) {
            var r = this, o = e.trim(a + ""), l = "." === o.slice(-1) ? "" : ".",
                s = void 0 !== t.responseJSON && void 0 !== t.responseJSON.error ? t.responseJSON.error : t.responseText;
            return r.cancelling && r.msgUploadAborted && (o = r.msgUploadAborted), r.showAjaxErrorDetails && s ? (s = e.trim(s.replace(/\n\s*\n/g, "\n")), s = s.length > 0 ? "<pre>" + s + "</pre>" : "", o += l + s) : o += l, o === l && (o = r.msgAjaxError.replace("{operation}", i)), r.cancelling = !1, n ? "<b>" + n + ": </b>" + o : o
        }, _parseFileType: function (e) {
            var t, a, n, r, o = this, l = o.allowedPreviewTypes || [];
            for (r = 0; r < l.length; r++) if (n = l[r], t = o.fileTypeSettings[n], a = t(e.type, e.name) ? n : "", !i.isEmpty(a)) return a;
            return "other"
        }, _getPreviewIcon: function (i) {
            var t, a = this, n = null;
            return i && i.indexOf(".") > -1 && (t = i.split(".").pop(), a.previewFileIconSettings && (n = a.previewFileIconSettings[t] || a.previewFileIconSettings[t.toLowerCase()] || null), a.previewFileExtSettings && e.each(a.previewFileExtSettings, function (e, i) {
                return a.previewFileIconSettings[e] && i(t) ? void(n = a.previewFileIconSettings[e]) : void 0
            })), n
        }, _parseFilePreviewIcon: function (e, i) {
            var t = this, a = t._getPreviewIcon(i) || t.previewFileIcon, n = e;
            return n.indexOf("{previewFileIcon}") > -1 && (n = n.setTokens({
                previewFileIconClass: t.previewFileIconClass,
                previewFileIcon: a
            })), n
        }, _raise: function (i, t) {
            var a = this, n = e.Event(i);
            if (void 0 !== t ? a.$element.trigger(n, t) : a.$element.trigger(n), n.isDefaultPrevented() || n.result === !1) return !1;
            switch (i) {
                case"filebatchuploadcomplete":
                case"filebatchuploadsuccess":
                case"fileuploaded":
                case"fileclear":
                case"filecleared":
                case"filereset":
                case"fileerror":
                case"filefoldererror":
                case"fileuploaderror":
                case"filebatchuploaderror":
                case"filedeleteerror":
                case"filecustomerror":
                case"filesuccessremove":
                    break;
                default:
                    a.ajaxAborted || (a.ajaxAborted = n.result)
            }
            return !0
        }, _listenFullScreen: function (e) {
            var i, t, a = this, n = a.$modal;
            n && n.length && (i = n && n.find(".btn-fullscreen"), t = n && n.find(".btn-borderless"), i.length && t.length && (i.removeClass("active").attr("aria-pressed", "false"), t.removeClass("active").attr("aria-pressed", "false"), e ? i.addClass("active").attr("aria-pressed", "true") : t.addClass("active").attr("aria-pressed", "true"), n.hasClass("file-zoom-fullscreen") ? a._maximizeZoomDialog() : e ? a._maximizeZoomDialog() : t.removeClass("active").attr("aria-pressed", "false")))
        }, _listen: function () {
            var t, a = this, n = a.$element, r = a.$form, o = a.$container;
            a._handler(n, "change", e.proxy(a._change, a)), a.showBrowse && a._handler(a.$btnFile, "click", e.proxy(a._browse, a)), a._handler(o.find(".fileinput-remove:not([disabled])"), "click", e.proxy(a.clear, a)), a._handler(o.find(".fileinput-cancel"), "click", e.proxy(a.cancel, a)), a._initDragDrop(), a._handler(r, "reset", e.proxy(a.reset, a)), a.isUploadable || a._handler(r, "submit", e.proxy(a._submitForm, a)), a._handler(a.$container.find(".fileinput-upload"), "click", e.proxy(a._uploadClick, a)), a._handler(e(window), "resize", function () {
                a._listenFullScreen(screen.width === window.innerWidth && screen.height === window.innerHeight)
            }), t = "webkitfullscreenchange mozfullscreenchange fullscreenchange MSFullscreenChange", a._handler(e(document), t, function () {
                a._listenFullScreen(i.checkFullScreen())
            }), a._initClickable()
        }, _initClickable: function () {
            var t, a = this;
            a.isClickable && (t = a.isUploadable ? a.$dropZone : a.$preview.find(".file-default-preview"), i.addCss(t, "clickable"), t.attr("tabindex", -1), a._handler(t, "click", function (i) {
                var n = e(i.target);
                n.parents(".file-preview-thumbnails").length && !n.parents(".file-default-preview").length || (a.$element.trigger("click"), t.blur())
            }))
        }, _initDragDrop: function () {
            var i = this, t = i.$dropZone;
            i.isUploadable && i.dropZoneEnabled && i.showPreview && (i._handler(t, "dragenter dragover", e.proxy(i._zoneDragEnter, i)), i._handler(t, "dragleave", e.proxy(i._zoneDragLeave, i)), i._handler(t, "drop", e.proxy(i._zoneDrop, i)), i._handler(e(document), "dragenter dragover drop", i._zoneDragDropInit))
        }, _zoneDragDropInit: function (e) {
            e.stopPropagation(), e.preventDefault()
        }, _zoneDragEnter: function (t) {
            var a = this, n = e.inArray("Files", t.originalEvent.dataTransfer.types) > -1;
            return a._zoneDragDropInit(t), a.isDisabled || !n ? (t.originalEvent.dataTransfer.effectAllowed = "none", void(t.originalEvent.dataTransfer.dropEffect = "none")) : void i.addCss(a.$dropZone, "file-highlighted")
        }, _zoneDragLeave: function (e) {
            var i = this;
            i._zoneDragDropInit(e), i.isDisabled || i.$dropZone.removeClass("file-highlighted")
        }, _zoneDrop: function (e) {
            var t = this;
            e.preventDefault(), t.isDisabled || i.isEmpty(e.originalEvent.dataTransfer.files) || (t._change(e, "dragdrop"), t.$dropZone.removeClass("file-highlighted"))
        }, _uploadClick: function (e) {
            var t, a = this, n = a.$container.find(".fileinput-upload"),
                r = !n.hasClass("disabled") && i.isEmpty(n.attr("disabled"));
            if (!e || !e.isDefaultPrevented()) {
                if (!a.isUploadable) return void(r && "submit" !== n.attr("type") && (t = n.closest("form"), t.length && t.trigger("submit"), e.preventDefault()));
                e.preventDefault(), r && a.upload()
            }
        }, _submitForm: function () {
            var e = this;
            return e._isFileSelectionValid() && !e._abort({})
        }, _clearPreview: function () {
            var t = this, a = t.$preview,
                n = t.showUploadedThumbs ? t.getFrames(":not(.file-preview-success)") : t.getFrames();
            n.each(function () {
                var t = e(this);
                t.remove(), i.cleanZoomCache(a.find("#zoom-" + t.attr("id")))
            }), t.getFrames().length && t.showPreview || t._resetUpload(), t._validateDefaultPreview()
        }, _initSortable: function () {
            var t, a = this, n = a.$preview, r = "." + i.SORT_CSS;
            window.KvSortable && 0 !== n.find(r).length && (t = {
                handle: ".drag-handle-init",
                dataIdAttr: "data-preview-id",
                scroll: !1,
                draggable: r,
                onSort: function (t) {
                    var n, r, o = t.oldIndex, l = t.newIndex;
                    a.initialPreview = i.moveArray(a.initialPreview, o, l), a.initialPreviewConfig = i.moveArray(a.initialPreviewConfig, o, l), a.previewCache.init();
                    for (var s = 0; s < a.initialPreviewConfig.length; s++) null !== a.initialPreviewConfig[s] && (n = a.initialPreviewConfig[s].key, r = e(".kv-file-remove[data-key='" + n + "']").closest(i.FRAMES), r.attr("data-fileindex", "init_" + s).attr("data-fileindex", "init_" + s));
                    a._raise("filesorted", {
                        previewId: e(t.item).attr("id"),
                        oldIndex: o,
                        newIndex: l,
                        stack: a.initialPreviewConfig
                    })
                }
            }, n.data("kvsortable") && n.kvsortable("destroy"), e.extend(!0, t, a.fileActionSettings.dragSettings), n.kvsortable(t))
        }, _initPreview: function (e) {
            var t, a = this, n = a.initialCaption || "";
            return a.previewCache.count() ? (t = a.previewCache.out(), n = e && a.initialCaption ? a.initialCaption : t.caption, a.$preview.html(t.content), a._setInitThumbAttr(), a._setCaption(n), a._initSortable(), void(i.isEmpty(t.content) || a.$container.removeClass("file-input-new"))) : (a._clearPreview(), void(e ? a._setCaption(n) : a._initCaption()))
        }, _getZoomButton: function (e) {
            var i = this, t = i.previewZoomButtonIcons[e], a = i.previewZoomButtonClasses[e],
                n = ' title="' + (i.previewZoomButtonTitles[e] || "") + '" ',
                r = n + ("close" === e ? ' data-dismiss="modal" aria-hidden="true"' : "");
            return "fullscreen" !== e && "borderless" !== e && "toggleheader" !== e || (r += ' data-toggle="button" aria-pressed="false" autocomplete="off"'), '<button type="button" class="' + a + " btn-" + e + '"' + r + ">" + t + "</button>"
        }, _getModalContent: function () {
            var e = this;
            return e._getLayoutTemplate("modal").setTokens({
                rtl: e.rtl ? " kv-rtl" : "",
                zoomFrameClass: e.frameClass,
                heading: e.msgZoomModalHeading,
                prev: e._getZoomButton("prev"),
                next: e._getZoomButton("next"),
                toggleheader: e._getZoomButton("toggleheader"),
                fullscreen: e._getZoomButton("fullscreen"),
                borderless: e._getZoomButton("borderless"),
                close: e._getZoomButton("close")
            })
        }, _listenModalEvent: function (e) {
            var t = this, a = t.$modal, n = function (e) {
                return {sourceEvent: e, previewId: a.data("previewId"), modal: a}
            };
            a.on(e + ".bs.modal", function (r) {
                var o = a.find(".btn-fullscreen"), l = a.find(".btn-borderless");
                t._raise("filezoom" + e, n(r)), "shown" === e && (l.removeClass("active").attr("aria-pressed", "false"), o.removeClass("active").attr("aria-pressed", "false"), a.hasClass("file-zoom-fullscreen") && (t._maximizeZoomDialog(), i.checkFullScreen() ? o.addClass("active").attr("aria-pressed", "true") : l.addClass("active").attr("aria-pressed", "true")))
            })
        }, _initZoom: function () {
            var t, a = this, n = a._getLayoutTemplate("modalMain"), r = "#" + i.MODAL_ID;
            a.showPreview && (a.$modal = e(r), a.$modal && a.$modal.length || (t = e(document.createElement("div")).html(n).insertAfter(a.$container), a.$modal = e(r).insertBefore(t), t.remove()), i.initModal(a.$modal), a.$modal.html(a._getModalContent()), e.each(i.MODAL_EVENTS, function (e, i) {
                a._listenModalEvent(i)
            }))
        }, _initZoomButtons: function () {
            var i, t, a = this, n = a.$modal.data("previewId") || "", r = a.getFrames().toArray(), o = r.length,
                l = a.$modal.find(".btn-prev"), s = a.$modal.find(".btn-next");
            return r.length < 2 ? (l.hide(), void s.hide()) : (l.show(), s.show(), void(o && (i = e(r[0]), t = e(r[o - 1]), l.removeAttr("disabled"), s.removeAttr("disabled"), i.length && i.attr("id") === n && l.attr("disabled", !0), t.length && t.attr("id") === n && s.attr("disabled", !0))))
        }, _maximizeZoomDialog: function () {
            var i = this, t = i.$modal, a = t.find(".modal-header:visible"), n = t.find(".modal-footer:visible"),
                r = t.find(".modal-body"), o = e(window).height(), l = 0;
            t.addClass("file-zoom-fullscreen"), a && a.length && (o -= a.outerHeight(!0)), n && n.length && (o -= n.outerHeight(!0)), r && r.length && (l = r.outerHeight(!0) - r.height(), o -= l), t.find(".kv-zoom-body").height(o)
        }, _resizeZoomDialog: function (e) {
            var t = this, a = t.$modal, n = a.find(".btn-fullscreen"), r = a.find(".btn-borderless");
            if (a.hasClass("file-zoom-fullscreen")) i.toggleFullScreen(!1), e ? n.hasClass("active") || (a.removeClass("file-zoom-fullscreen"), t._resizeZoomDialog(!0), r.hasClass("active") && r.removeClass("active").attr("aria-pressed", "false")) : n.hasClass("active") ? n.removeClass("active").attr("aria-pressed", "false") : (a.removeClass("file-zoom-fullscreen"), t.$modal.find(".kv-zoom-body").css("height", t.zoomModalHeight)); else {
                if (!e) return void t._maximizeZoomDialog();
                i.toggleFullScreen(!0)
            }
            a.focus()
        }, _setZoomContent: function (t, a) {
            var n, r, o, l, s, d, c, u, p, f, m = this, v = t.attr("id"), h = m.$modal, g = h.find(".btn-prev"),
                w = h.find(".btn-next"), _ = h.find(".btn-fullscreen"), b = h.find(".btn-borderless"),
                C = h.find(".btn-toggleheader"), y = m.$preview.find("#zoom-" + v);
            r = y.attr("data-template") || "generic", n = y.find(".kv-file-content"), o = n.length ? n.html() : "", p = t.data("caption") || "", f = t.data("size") || "", l = p + " " + f, h.find(".kv-zoom-title").html(l), s = h.find(".kv-zoom-body"), h.removeClass("kv-single-content"), a ? (u = s.addClass("file-thumb-loading").clone().insertAfter(s), s.html(o).hide(), u.fadeOut("fast", function () {
                s.fadeIn("fast", function () {
                    s.removeClass("file-thumb-loading")
                }), u.remove()
            })) : s.html(o), c = m.previewZoomSettings[r], c && (d = s.find(".kv-preview-data"), i.addCss(d, "file-zoom-detail"), e.each(c, function (e, i) {
                d.css(e, i), (d.attr("width") && "width" === e || d.attr("height") && "height" === e) && d.removeAttr(e)
            })), h.data("previewId", v);
            var T = s.find("img");
            T.length && i.adjustOrientedImage(T, !0), m._handler(g, "click", function () {
                m._zoomSlideShow("prev", v)
            }), m._handler(w, "click", function () {
                m._zoomSlideShow("next", v)
            }), m._handler(_, "click", function () {
                m._resizeZoomDialog(!0)
            }), m._handler(b, "click", function () {
                m._resizeZoomDialog(!1)
            }), m._handler(C, "click", function () {
                var e, i = h.find(".modal-header"), t = h.find(".modal-body .floating-buttons"),
                    a = i.find(".kv-zoom-actions"), n = function (e) {
                        var t = m.$modal.find(".kv-zoom-body"), a = m.zoomModalHeight;
                        h.hasClass("file-zoom-fullscreen") && (a = t.outerHeight(!0), e || (a -= i.outerHeight(!0))), t.css("height", e ? a + e : a)
                    };
                i.is(":visible") ? (e = i.outerHeight(!0), i.slideUp("slow", function () {
                    a.find(".btn").appendTo(t), n(e)
                })) : (t.find(".btn").appendTo(a), i.slideDown("slow", function () {
                    n()
                })), h.focus()
            }), m._handler(h, "keydown", function (e) {
                var i = e.which || e.keyCode;
                37 !== i || g.attr("disabled") || m._zoomSlideShow("prev", v), 39 !== i || w.attr("disabled") || m._zoomSlideShow("next", v)
            })
        }, _zoomPreview: function (e) {
            var t, a = this, n = a.$modal;
            if (!e.length) throw"Cannot zoom to detailed preview!";
            i.initModal(n), n.html(a._getModalContent()), t = e.closest(i.FRAMES), a._setZoomContent(t), n.modal("show"), a._initZoomButtons()
        }, _zoomSlideShow: function (i, t) {
            var a, n, r, o = this, l = o.$modal.find(".kv-zoom-actions .btn-" + i), s = o.getFrames().toArray(),
                d = s.length;
            if (!l.attr("disabled")) {
                for (n = 0; d > n; n++) if (e(s[n]).attr("id") === t) {
                    r = "prev" === i ? n - 1 : n + 1;
                    break
                }
                0 > r || r >= d || !s[r] || (a = e(s[r]), a.length && o._setZoomContent(a, !0), o._initZoomButtons(), o._raise("filezoom" + i, {
                    previewId: t,
                    modal: o.$modal
                }))
            }
        }, _initZoomButton: function () {
            var i = this;
            i.$preview.find(".kv-file-zoom").each(function () {
                var t = e(this);
                i._handler(t, "click", function () {
                    i._zoomPreview(t)
                })
            })
        }, _clearObjects: function (i) {
            i.find("video audio").each(function () {
                this.pause(), e(this).remove()
            }), i.find("img object div").each(function () {
                e(this).remove()
            })
        }, _clearFileInput: function () {
            var t, a, n, r = this, o = r.$element;
            r.fileInputCleared = !0, i.isEmpty(o.val()) || (r.isIE9 || r.isIE10 ? (t = o.closest("form"), a = e(document.createElement("form")), n = e(document.createElement("div")), o.before(n), t.length ? t.after(a) : n.after(a), a.append(o).trigger("reset"), n.before(o).remove(), a.remove()) : o.val(""))
        }, _resetUpload: function () {
            var e = this;
            e.uploadCache = {
                content: [],
                config: [],
                tags: [],
                append: !0
            }, e.uploadCount = 0, e.uploadStatus = {}, e.uploadLog = [], e.uploadAsyncCount = 0, e.loadedImages = [], e.totalImagesCount = 0, e.$btnUpload.removeAttr("disabled"), e._setProgress(0), i.addCss(e.$progress, "hide"), e._resetErrors(!1), e.ajaxAborted = !1, e.ajaxRequests = [], e._resetCanvas(), e.cacheInitialPreview = {}, e.overwriteInitial && (e.initialPreview = [], e.initialPreviewConfig = [], e.initialPreviewThumbTags = [], e.previewCache.data = {
                content: [],
                config: [],
                tags: []
            })
        }, _resetCanvas: function () {
            var e = this;
            e.canvas && e.imageCanvasContext && e.imageCanvasContext.clearRect(0, 0, e.canvas.width, e.canvas.height)
        }, _hasInitialPreview: function () {
            var e = this;
            return !e.overwriteInitial && e.previewCache.count()
        }, _resetPreview: function () {
            var e, i, t = this;
            t.previewCache.count() ? (e = t.previewCache.out(), t.$preview.html(e.content), t._setInitThumbAttr(), i = t.initialCaption ? t.initialCaption : e.caption, t._setCaption(i)) : (t._clearPreview(), t._initCaption()), t.showPreview && (t._initZoom(), t._initSortable())
        }, _clearDefaultPreview: function () {
            var e = this;
            e.$preview.find(".file-default-preview").remove()
        }, _validateDefaultPreview: function () {
            var e = this;
            e.showPreview && !i.isEmpty(e.defaultPreviewContent) && (e.$preview.html('<div class="file-default-preview">' + e.defaultPreviewContent + "</div>"), e.$container.removeClass("file-input-new"), e._initClickable())
        }, _resetPreviewThumbs: function (e) {
            var i, t = this;
            return e ? (t._clearPreview(), void t.clearStack()) : void(t._hasInitialPreview() ? (i = t.previewCache.out(), t.$preview.html(i.content), t._setInitThumbAttr(), t._setCaption(i.caption), t._initPreviewActions()) : t._clearPreview())
        }, _getLayoutTemplate: function (e) {
            var t = this, a = t.layoutTemplates[e];
            return i.isEmpty(t.customLayoutTags) ? a : i.replaceTags(a, t.customLayoutTags)
        }, _getPreviewTemplate: function (e) {
            var t = this, a = t.previewTemplates[e];
            return i.isEmpty(t.customPreviewTags) ? a : i.replaceTags(a, t.customPreviewTags)
        }, _getOutData: function (e, i, t) {
            var a = this;
            return e = e || {}, i = i || {}, t = t || a.filestack.slice(0) || {}, {
                form: a.formdata,
                files: t,
                filenames: a.filenames,
                filescount: a.getFilesCount(),
                extra: a._getExtraData(),
                response: i,
                reader: a.reader,
                jqXHR: e
            }
        }, _getMsgSelected: function (e) {
            var i = this, t = 1 === e ? i.fileSingle : i.filePlural;
            return e > 0 ? i.msgSelected.replace("{n}", e).replace("{files}", t) : i.msgNoFilesSelected
        }, _getFrame: function (i) {
            var t = this, a = e("#" + i);
            return a.length ? a : (t._log('Invalid thumb frame with id: "' + i + '".'), null)
        }, _getThumbs: function (e) {
            return e = e || "", this.getFrames(":not(.file-preview-initial)" + e)
        }, _getExtraData: function (e, i) {
            var t = this, a = t.uploadExtraData;
            return "function" == typeof t.uploadExtraData && (a = t.uploadExtraData(e, i)), a
        }, _initXhr: function (e, i, t) {
            var a = this;
            return e.upload && e.upload.addEventListener("progress", function (e) {
                var n = 0, r = e.total, o = e.loaded || e.position;
                e.lengthComputable && (n = Math.floor(o / r * 100)), i ? a._setAsyncUploadStatus(i, n, t) : a._setProgress(n)
            }, !1), e
        }, _ajaxSubmit: function (i, t, a, n, r, o) {
            var l, s = this;
            s._raise("filepreajax", [r, o]) && (s._uploadExtra(r, o), l = e.extend(!0, {}, {
                xhr: function () {
                    var i = e.ajaxSettings.xhr();
                    return s._initXhr(i, r, s.getFileStack().length)
                },
                url: s.uploadUrl,
                type: "POST",
                dataType: "json",
                data: s.formdata,
                cache: !1,
                processData: !1,
                contentType: !1,
                beforeSend: i,
                success: t,
                complete: a,
                error: n
            }, s.ajaxSettings), s.ajaxRequests.push(e.ajax(l)))
        }, _mergeArray: function (e, t) {
            var a = this, n = i.cleanArray(a[e]), r = i.cleanArray(t);
            a[e] = n.concat(r)
        }, _initUploadSuccess: function (t, a, n) {
            var r, o, l, s, d, c, u, p, f, m = this;
            m.showPreview && "object" == typeof t && !e.isEmptyObject(t) && void 0 !== t.initialPreview && t.initialPreview.length > 0 && (m.hasInitData = !0, c = t.initialPreview || [], u = t.initialPreviewConfig || [], p = t.initialPreviewThumbTags || [], r = !(void 0 !== t.append && !t.append), c.length > 0 && !i.isArray(c) && (c = c.split(m.initialPreviewDelimiter)), m._mergeArray("initialPreview", c), m._mergeArray("initialPreviewConfig", u), m._mergeArray("initialPreviewThumbTags", p), void 0 !== a ? n ? (f = a.attr("data-fileindex"), m.uploadCache.content[f] = c[0], m.uploadCache.config[f] = u[0] || [], m.uploadCache.tags[f] = p[0] || [], m.uploadCache.append = r) : (l = m.previewCache.add(c, u[0], p[0], r), o = m.previewCache.get(l, !1), s = e(document.createElement("div")).html(o).hide().insertAfter(a), d = s.find(".kv-zoom-cache"), d && d.length && d.insertAfter(a), a.fadeOut("slow", function () {
                var e = s.find(".file-preview-frame");
                e && e.length && e.insertBefore(a).fadeIn("slow").css("display:inline-block"), m._initPreviewActions(), m._clearFileInput(), i.cleanZoomCache(m.$preview.find("#zoom-" + a.attr("id"))), a.remove(), s.remove(), m._initSortable()
            })) : (m.previewCache.set(c, u, p, r), m._initPreview(), m._initPreviewActions()))
        }, _initSuccessThumbs: function () {
            var t = this;
            t.showPreview && t._getThumbs(i.FRAMES + ".file-preview-success").each(function () {
                var a = e(this), n = t.$preview, r = a.find(".kv-file-remove");
                r.removeAttr("disabled"), t._handler(r, "click", function () {
                    var e = a.attr("id"), r = t._raise("filesuccessremove", [e, a.attr("data-fileindex")]);
                    i.cleanMemory(a), r !== !1 && a.fadeOut("slow", function () {
                        i.cleanZoomCache(n.find("#zoom-" + e)), a.remove(), t.getFrames().length || t.reset()
                    })
                })
            })
        }, _checkAsyncComplete: function () {
            var i, t, a = this;
            for (t = 0; t < a.filestack.length; t++) if (a.filestack[t] && (i = a.previewInitId + "-" + t, -1 === e.inArray(i, a.uploadLog))) return !1;
            return a.uploadAsyncCount === a.uploadLog.length
        }, _uploadExtra: function (i, t) {
            var a = this, n = a._getExtraData(i, t);
            0 !== n.length && e.each(n, function (e, i) {
                a.formdata.append(e, i)
            })
        }, _uploadSingle: function (t, a, n) {
            var r, o, l, s, d, c, u, p, f, m, v, h = this, g = h.getFileStack().length, w = new FormData,
                _ = h.previewInitId + "-" + t, b = h.filestack.length > 0 || !e.isEmptyObject(h.uploadExtraData),
                C = e("#" + _).find(".file-thumb-progress"), y = {id: _, index: t},
                T = !i.isEmpty(h.$element.attr("multiple"));
            h.formdata = w, h.showPreview && (o = e("#" + _ + ":not(.file-preview-initial)"), s = o.find(".kv-file-upload"), d = o.find(".kv-file-remove"), C.removeClass("hide")), 0 === g || !b || s && s.hasClass("disabled") || h._abort(y) || (m = function (e, i) {
                !T && v || h.updateStack(e, void 0), h.uploadLog.push(i), h._checkAsyncComplete() && (h.fileBatchCompleted = !0)
            }, l = function () {
                var e, t, a, n = h.uploadCache, r = 0, o = h.cacheInitialPreview;
                h.fileBatchCompleted && (o && o.content && (r = o.content.length), setTimeout(function () {
                    var l = T || !v;
                    if (h.showPreview) {
                        if (h.previewCache.set(n.content, n.config, n.tags, n.append), r) {
                            for (t = 0; t < n.content.length; t++) a = t + r, o.content[a] = n.content[t], o.config.length && (o.config[a] = n.config[t]), o.tags.length && (o.tags[a] = n.tags[t]);
                            h.initialPreview = i.cleanArray(o.content), h.initialPreviewConfig = i.cleanArray(o.config), h.initialPreviewThumbTags = i.cleanArray(o.tags)
                        } else h.initialPreview = n.content, h.initialPreviewConfig = n.config, h.initialPreviewThumbTags = n.tags;
                        h.cacheInitialPreview = {}, h.hasInitData && (h._initPreview(), h._initPreviewActions())
                    }
                    h.unlock(l), l && h._clearFileInput(), e = h.$preview.find(".file-preview-initial"), h.uploadAsync && e.length && (i.addCss(e, i.SORT_CSS), h._initSortable()), h._raise("filebatchuploadcomplete", [h.filestack, h._getExtraData()]), h.uploadCount = 0, h.uploadStatus = {}, h.uploadLog = [], h._setProgress(101)
                }, 100))
            }, c = function (a) {
                r = h._getOutData(a), h.fileBatchCompleted = !1, h.showPreview && (o.hasClass("file-preview-success") || (h._setThumbStatus(o, "Loading"), i.addCss(o, "file-uploading")), s.attr("disabled", !0), d.attr("disabled", !0)), n || h.lock(), h._raise("filepreupload", [r, _, t]), e.extend(!0, y, r), h._abort(y) && (a.abort(), h._setProgressCancelled())
            }, u = function (a, l, d) {
                var c = h.showPreview && o.attr("id") ? o.attr("id") : _;
                r = h._getOutData(d, a), e.extend(!0, y, r), setTimeout(function () {
                    i.isEmpty(a) || i.isEmpty(a.error) ? (h.showPreview && (h._setThumbStatus(o, "Success"), s.hide(), h._initUploadSuccess(a, o, n), h._setProgress(101, C)), h._raise("fileuploaded", [r, c, t]), n ? m(t, c) : h.updateStack(t, void 0)) : (v = !0, h._showUploadError(a.error, y), h._setPreviewError(o, t, T ? null : h.filestack[t]), n && m(t, c))
                }, 100)
            }, p = function () {
                setTimeout(function () {
                    h.showPreview && (s.removeAttr("disabled"), d.removeAttr("disabled"), o.removeClass("file-uploading")), n ? l() : (h.unlock(!1), h._clearFileInput()), h._initSuccessThumbs()
                }, 100)
            }, f = function (i, r, l) {
                var s = h.ajaxOperations.uploadThumb, d = h._parseError(s, i, l, n ? a[t].name : null);
                v = !0, setTimeout(function () {
                    n && m(t, _), h.uploadStatus[_] = 100, h._setPreviewError(o, t, T ? null : h.filestack[t]), e.extend(!0, y, h._getOutData(i)), h._setProgress(101, C, h.msgAjaxProgressError.replace("{operation}", s)), h._showUploadError(d, y)
                }, 100)
            }, w.append(h.uploadFileAttr, a[t], h.filenames[t]), w.append("file_id", t), h._ajaxSubmit(c, u, p, f, _, t))
        }, _uploadBatch: function () {
            var t, a, n, r, o, l = this, s = l.filestack, d = s.length, c = {},
                u = l.filestack.length > 0 || !e.isEmptyObject(l.uploadExtraData);
            l.formdata = new FormData, 0 !== d && u && !l._abort(c) && (o = function () {
                e.each(s, function (e) {
                    l.updateStack(e, void 0)
                }), l._clearFileInput()
            }, t = function (t) {
                l.lock();
                var a = l._getOutData(t);
                l.showPreview && l._getThumbs().each(function () {
                    var t = e(this), a = t.find(".kv-file-upload"), n = t.find(".kv-file-remove");
                    t.hasClass("file-preview-success") || (l._setThumbStatus(t, "Loading"), i.addCss(t, "file-uploading")), a.attr("disabled", !0), n.attr("disabled", !0)
                }), l._raise("filebatchpreupload", [a]), l._abort(a) && (t.abort(), l._setProgressCancelled())
            }, a = function (t, a, n) {
                var r = l._getOutData(n, t), s = l._getThumbs(":not(.file-preview-error)"), d = 0,
                    c = i.isEmpty(t) || i.isEmpty(t.errorkeys) ? [] : t.errorkeys;
                i.isEmpty(t) || i.isEmpty(t.error) ? (l._raise("filebatchuploadsuccess", [r]), o(), l.showPreview ? (s.each(function () {
                    var i = e(this), t = i.find(".kv-file-upload");
                    i.find(".kv-file-upload").hide(), l._setThumbStatus(i, "Success"), i.removeClass("file-uploading"), t.removeAttr("disabled")
                }), l._initUploadSuccess(t)) : l.reset(), l._setProgress(101)) : (l.showPreview && (s.each(function () {
                    var i = e(this), t = i.find(".kv-file-remove"), a = i.find(".kv-file-upload");
                    return i.removeClass("file-uploading"), a.removeAttr("disabled"), t.removeAttr("disabled"), 0 === c.length ? void l._setPreviewError(i) : (-1 !== e.inArray(d, c) ? l._setPreviewError(i) : (i.find(".kv-file-upload").hide(), l._setThumbStatus(i, "Success"), l.updateStack(d, void 0)), void d++)
                }), l._initUploadSuccess(t)), l._showUploadError(t.error, r, "filebatchuploaderror"))
            }, r = function () {
                l.unlock(), l._initSuccessThumbs(), l._clearFileInput(), l._raise("filebatchuploadcomplete", [l.filestack, l._getExtraData()])
            }, n = function (i, t, a) {
                var n = l._getOutData(i), r = l.ajaxOperations.uploadBatch, o = l._parseError(r, i, a);
                l._showUploadError(o, n, "filebatchuploaderror"), l.uploadFileCount = d - 1, l.showPreview && (l._getThumbs().each(function () {
                    var i = e(this), t = i.attr("data-fileindex");
                    i.removeClass("file-uploading"), void 0 !== l.filestack[t] && l._setPreviewError(i)
                }), l._getThumbs().removeClass("file-uploading"), l._getThumbs(" .kv-file-upload").removeAttr("disabled"), l._getThumbs(" .kv-file-delete").removeAttr("disabled"), l._setProgress(101, l.$progress, l.msgAjaxProgressError.replace("{operation}", r)))
            }, e.each(s, function (e, t) {
                i.isEmpty(s[e]) || l.formdata.append(l.uploadFileAttr, t, l.filenames[e])
            }), l._ajaxSubmit(t, a, r, n))
        }, _uploadExtraOnly: function () {
            var e, t, a, n, r = this, o = {};
            r.formdata = new FormData, r._abort(o) || (e = function (e) {
                r.lock();
                var i = r._getOutData(e);
                r._raise("filebatchpreupload", [i]), r._setProgress(50), o.data = i, o.xhr = e, r._abort(o) && (e.abort(), r._setProgressCancelled())
            }, t = function (e, t, a) {
                var n = r._getOutData(a, e);
                i.isEmpty(e) || i.isEmpty(e.error) ? (r._raise("filebatchuploadsuccess", [n]), r._clearFileInput(), r._initUploadSuccess(e), r._setProgress(101)) : r._showUploadError(e.error, n, "filebatchuploaderror")
            }, a = function () {
                r.unlock(), r._clearFileInput(), r._raise("filebatchuploadcomplete", [r.filestack, r._getExtraData()])
            }, n = function (e, i, t) {
                var a = r._getOutData(e), n = r.ajaxOperations.uploadExtra, l = r._parseError(n, e, t);
                o.data = a, r._showUploadError(l, a, "filebatchuploaderror"), r._setProgress(101, r.$progress, r.msgAjaxProgressError.replace("{operation}", n))
            }, r._ajaxSubmit(e, t, a, n))
        }, _deleteFileIndex: function (t) {
            var a = this, n = t.attr("data-fileindex");
            "init_" === n.substring(0, 5) && (n = parseInt(n.replace("init_", "")), a.initialPreview = i.spliceArray(a.initialPreview, n), a.initialPreviewConfig = i.spliceArray(a.initialPreviewConfig, n), a.initialPreviewThumbTags = i.spliceArray(a.initialPreviewThumbTags, n), a.getFrames().each(function () {
                var i = e(this), t = i.attr("data-fileindex");
                "init_" === t.substring(0, 5) && (t = parseInt(t.replace("init_", "")), t > n && (t--, i.attr("data-fileindex", "init_" + t)))
            }), a.uploadAsync && (a.cacheInitialPreview = a.getPreview()))
        }, _initFileActions: function () {
            var t = this, a = t.$preview;
            t.showPreview && (t._initZoomButton(), t.getFrames(" .kv-file-remove").each(function () {
                var n, r, o, l, s = e(this), d = s.closest(i.FRAMES), c = d.attr("id"), u = d.attr("data-fileindex");
                t._handler(s, "click", function () {
                    return l = t._raise("filepreremove", [c, u]), l !== !1 && t._validateMinCount() ? (n = d.hasClass("file-preview-error"), i.cleanMemory(d), void d.fadeOut("slow", function () {
                        i.cleanZoomCache(a.find("#zoom-" + c)), t.updateStack(u, void 0), t._clearObjects(d), d.remove(), c && n && t.$errorContainer.find('li[data-file-id="' + c + '"]').fadeOut("fast", function () {
                            e(this).remove(), t._errorsExist() || t._resetErrors()
                        }), t._clearFileInput();
                        var l = t.getFileStack(!0), s = t.previewCache.count(), p = l.length,
                            f = t.showPreview && t.getFrames().length;
                        0 !== p || 0 !== s || f ? (r = s + p, o = r > 1 ? t._getMsgSelected(r) : l[0] ? t._getFileNames()[0] : "", t._setCaption(o)) : t.reset(), t._raise("fileremoved", [c, u])
                    })) : !1
                })
            }), t.getFrames(" .kv-file-upload").each(function () {
                var a = e(this);
                t._handler(a, "click", function () {
                    var e = a.closest(i.FRAMES), n = e.attr("data-fileindex");
                    e.hasClass("file-preview-error") || t._uploadSingle(n, t.filestack, !1)
                })
            }))
        }, _initPreviewActions: function () {
            var t = this, a = t.$preview, n = t.deleteExtraData || {}, r = i.FRAMES + " .kv-file-remove",
                o = function () {
                    var e = t.isUploadable ? t.previewCache.count() : t.$element.get(0).files.length;
                    0 !== a.find(r).length || e || (t.reset(), t.initialCaption = "")
                };
            t._initZoomButton(), a.find(r).each(function () {
                var r = e(this), l = r.data("url") || t.deleteUrl, s = r.data("key");
                if (!i.isEmpty(l) && void 0 !== s) {
                    var d, c, u, p, f = r.closest(i.FRAMES), m = t.previewCache.data, v = f.attr("data-fileindex");
                    v = parseInt(v.replace("init_", "")), u = i.isEmpty(m.config) && i.isEmpty(m.config[v]) ? null : m.config[v], p = i.isEmpty(u) || i.isEmpty(u.extra) ? n : u.extra, "function" == typeof p && (p = p()), c = {
                        id: r.attr("id"),
                        key: s,
                        extra: p
                    }, d = e.extend(!0, {}, {
                        url: l,
                        type: "POST",
                        dataType: "json",
                        data: e.extend(!0, {}, {key: s}, p),
                        beforeSend: function (e) {
                            t.ajaxAborted = !1, t._raise("filepredelete", [s, e, p]), t.ajaxAborted ? e.abort() : (i.addCss(f, "file-uploading"), i.addCss(r, "disabled"))
                        },
                        success: function (e, n, l) {
                            var d, u;
                            return i.isEmpty(e) || i.isEmpty(e.error) ? (v = parseInt(f.attr("data-fileindex").replace("init_", "")), t.previewCache.unset(v), d = t.previewCache.count(), u = d > 0 ? t._getMsgSelected(d) : "", t._deleteFileIndex(f), t._setCaption(u), t._raise("filedeleted", [s, l, p]), f.removeClass("file-uploading").addClass("file-deleted"), void f.fadeOut("slow", function () {
                                i.cleanZoomCache(a.find("#zoom-" + f.attr("id"))), t._clearObjects(f), f.remove(), o(), d || 0 !== t.getFileStack().length || (t._setCaption(""), t.reset())
                            })) : (c.jqXHR = l, c.response = e, t._showError(e.error, c, "filedeleteerror"), f.removeClass("file-uploading"), r.removeClass("disabled"), void o())
                        },
                        error: function (e, i, a) {
                            var n = t.ajaxOperations.deleteThumb, r = t._parseError(n, e, a);
                            c.jqXHR = e, c.response = {}, t._showError(r, c, "filedeleteerror"), f.removeClass("file-uploading"), o()
                        }
                    }, t.ajaxDeleteSettings), t._handler(r, "click", function () {
                        // return t._validateMinCount() ? void e.ajax(d) : !1
                        if(!t._validateMinCount()){
                            return !1;
                        }
                        swal({title: '',
                            text: "确认删除图片?删除后不可恢复。",
                            type: 'warning',
                            showCancelButton: true,
                            closeOnConfirm: true,
                            cancelButtonText:"取消",
                            confirmButtonText: '确认',
                            confirmButtonColor:"#ec6c62"
                        },function (){
                            return void e.ajax(d);
                            location.reload();
                        })
                    })
                }
            })
        }, _hideFileIcon: function () {
            this.overwriteInitial && this.$captionContainer.find(".kv-caption-icon").hide()
        }, _showFileIcon: function () {
            this.$captionContainer.find(".kv-caption-icon").show()
        }, _getSize: function (i) {
            var t, a, n, r = this, o = parseFloat(i), l = r.fileSizeGetter;
            return e.isNumeric(i) && e.isNumeric(o) ? ("function" == typeof l ? n = l(o) : 0 === o ? n = "0.00 B" : (t = Math.floor(Math.log(o) / Math.log(1024)), a = ["B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"], n = 1 * (o / Math.pow(1024, t)).toFixed(2) + " " + a[t]), r._getLayoutTemplate("size").replace("{sizeText}", n)) : ""
        }, _generatePreviewTemplate: function (t, a, n, r, o, l, s, d, c, u, p) {
            var f, m = this, v = m.slug(n), h = "", g = m.previewSettings[t] || m.defaults.previewSettings[t],
                w = g && g.width ? g.width : "", _ = g && g.height ? g.height : "",
                b = c || m._renderFileFooter(v, s, i.isEmpty(w) ? "auto" : w, l), C = m._getPreviewIcon(n),
                y = "type-default", T = C && m.preferIconicPreview, E = C && m.preferIconicZoomPreview,
                x = function (a, l, s, c) {
                    var f = s ? "zoom-" + o : o, h = m._getPreviewTemplate(a), g = (d || "") + " " + c;
                    return m.frameClass && (g = m.frameClass + " " + g), s && (g = g.replace(" " + i.SORT_CSS, "")), h = m._parseFilePreviewIcon(h, n), "text" === a && (l = i.htmlEncode(l)), "object" !== t || r || e.each(m.defaults.fileTypeSettings, function (e, i) {
                        "object" !== e && "other" !== e && i(n, r) && (y = "type-" + e)
                    }), h.setTokens({
                        previewId: f,
                        caption: v,
                        frameClass: g,
                        type: r,
                        fileindex: u,
                        width: w,
                        height: _,
                        typeCss: y,
                        footer: b,
                        data: l,
                        template: p || t
                    })
                };
            return u = u || o.slice(o.lastIndexOf("-") + 1), m.fileActionSettings.showZoom && (h = x(E ? "other" : t, a, !0, "kv-zoom-thumb")), h = "\n" + m._getLayoutTemplate("zoomCache").replace("{zoomContent}", h), f = x(T ? "other" : t, a, !1, "kv-preview-thumb"), f + h
        }, _previewDefault: function (t, a, n) {
            var r = this, o = r.$preview;
            if (r.showPreview) {
                var l, s = t ? t.name : "", d = t ? t.type : "", c = t.size || 0, u = r.slug(s),
                    p = n === !0 && !r.isUploadable, f = i.objUrl.createObjectURL(t);
                r._clearDefaultPreview(), l = r._generatePreviewTemplate("other", f, s, d, a, p, c), o.append("\n" + l), r._setThumbAttr(a, u, c), n === !0 && r.isUploadable && r._setThumbStatus(e("#" + a), "Error")
            }
        }, _previewFile: function (e, t, a, n, r) {
            if (this.showPreview) {
                var o, l = this, s = l._parseFileType(t), d = t ? t.name : "", c = l.slug(d), u = l.allowedPreviewTypes,
                    p = l.allowedPreviewMimeTypes, f = l.$preview, m = u && u.indexOf(s) >= 0, v = t.size || 0,
                    h = t.type, g = "text" === s || "html" === s || "image" === s ? a.target.result : r,
                    w = p && -1 !== p.indexOf(h);
                if ("html" === s && l.purifyHtml && window.DOMPurify && (g = window.DOMPurify.sanitize(g)), m || w) {
                    o = l._generatePreviewTemplate(s, g, d, h, n, !1, v), l._clearDefaultPreview(), f.append("\n" + o);
                    var _ = f.find("#" + n + " img");
                    _.length && l.autoOrientImage ? i.validateOrientation(t, function (e) {
                        if (e) {
                            var a = f.find("#zoom-" + n + " img"), r = "rotate-" + e;
                            e > 4 && (r += _.width() > _.height() ? " is-portrait-gt4" : " is-landscape-gt4"), i.addCss(_, r), i.addCss(a, r), l._raise("fileimageoriented", {
                                $img: _,
                                file: t
                            })
                        }
                        l._validateImage(n, c, h, v, g), i.adjustOrientedImage(_)
                    }) : l._validateImage(n, c, h, v, g)
                } else l._previewDefault(t, n);
                l._setThumbAttr(n, c, v), l._initSortable()
            }
        }, _setThumbAttr: function (i, t, a) {
            var n = this, r = e("#" + i);
            r.length && (a = a && a > 0 ? n._getSize(a) : "", r.data({caption: t, size: a}))
        }, _setInitThumbAttr: function () {
            var e, t, a, n, r = this, o = r.previewCache.data, l = r.previewCache.count(!0);
            if (0 !== l) for (var s = 0; l > s; s++) e = o.config[s], n = r.previewInitId + "-init_" + s, t = i.ifSet("caption", e, i.ifSet("filename", e)), a = i.ifSet("size", e), r._setThumbAttr(n, t, a)
        }, _slugDefault: function (e) {
            return i.isEmpty(e) ? "" : String(e).replace(/[\-\[\]\/\{}:;#%=\(\)\*\+\?\\\^\$\|<>&"']/g, "_")
        }, _readFiles: function (t) {
            this.reader = new FileReader;
            var a, n = this, r = n.$element, o = n.$preview, l = n.reader, s = n.$previewContainer,
                d = n.$previewStatus, c = n.msgLoading, u = n.msgProgress, p = n.previewInitId, f = t.length,
                m = n.fileTypeSettings, v = n.filestack.length, h = n.allowedFileTypes, g = h ? h.length : 0,
                w = n.allowedFileExtensions, _ = i.isEmpty(w) ? "" : w.join(", "),
                b = n.maxFilePreviewSize && parseFloat(n.maxFilePreviewSize), C = o.length && (!b || isNaN(b)),
                y = function (i, r, o, l) {
                    var s = e.extend(!0, {}, n._getOutData({}, {}, t), {id: o, index: l}),
                        d = {id: o, index: l, file: r, files: t};
                    return n._previewDefault(r, o, !0), n.isUploadable && (n.addToStack(void 0), setTimeout(function () {
                        a(l + 1)
                    }, 100)), n._initFileActions(), n.removeFromPreviewOnError && e("#" + o).remove(), n.isUploadable ? n._showUploadError(i, s) : n._showError(i, d)
                };
            n.loadedImages = [], n.totalImagesCount = 0, e.each(t, function (e, i) {
                var t = n.fileTypeSettings.image;
                t && t(i.type) && n.totalImagesCount++
            }), a = function (e) {
                if (i.isEmpty(r.attr("multiple")) && (f = 1), e >= f) return n.isUploadable && n.filestack.length > 0 ? n._raise("filebatchselected", [n.getFileStack()]) : n._raise("filebatchselected", [t]), s.removeClass("file-thumb-loading"), void d.html("");
                var T, E, x, F, S, I, k, P, z, A, $ = v + e, D = p + "-" + $, U = t[e],
                    B = U.name ? n.slug(U.name) : "", j = (U.size || 0) / 1e3, L = "", O = i.objUrl.createObjectURL(U),
                    R = 0, Z = "";
                if (g > 0) for (F = 0; g > F; F++) P = h[F], z = n.msgFileTypes[P] || P, Z += 0 === F ? z : ", " + z;
                if (B === !1) return void a(e + 1);
                if (0 === B.length) return S = n.msgInvalidFileName.replace("{name}", i.htmlEncode(U.name)), void(n.isError = y(S, U, D, e));
                if (i.isEmpty(w) || (L = new RegExp("\\.(" + w.join("|") + ")$", "i")), x = j.toFixed(2), n.maxFileSize > 0 && j > n.maxFileSize) return S = n.msgSizeTooLarge.setTokens({
                    name: B,
                    size: x,
                    maxSize: n.maxFileSize
                }), void(n.isError = y(S, U, D, e));
                if (null !== n.minFileSize && j <= i.getNum(n.minFileSize)) return S = n.msgSizeTooSmall.setTokens({
                    name: B,
                    size: x,
                    minSize: n.minFileSize
                }), void(n.isError = y(S, U, D, e));
                if (!i.isEmpty(h) && i.isArray(h)) {
                    for (F = 0; F < h.length; F += 1) I = h[F], A = m[I], R += A && "function" == typeof A && A(U.type, U.name) ? 1 : 0;
                    if (0 === R) return S = n.msgInvalidFileType.setTokens({
                        name: B,
                        types: Z
                    }), void(n.isError = y(S, U, D, e))
                }
                return 0 !== R || i.isEmpty(w) || !i.isArray(w) || i.isEmpty(L) || (k = i.compare(B, L), R += i.isEmpty(k) ? 0 : k.length, 0 !== R) ? n.showPreview ? !C && j > b ? (n.addToStack(U), s.addClass("file-thumb-loading"), n._previewDefault(U, D), n._initFileActions(), n._updateFileDetails(f), void a(e + 1)) : (o.length && void 0 !== FileReader ? (d.html(c.replace("{index}", e + 1).replace("{files}", f)), s.addClass("file-thumb-loading"), l.onerror = function (e) {
                    n._errorHandler(e, B)
                }, l.onload = function (i) {
                    n._previewFile(e, U, i, D, O), n._initFileActions()
                }, l.onloadend = function () {
                    S = u.setTokens({index: e + 1, files: f, percent: 50, name: B}), setTimeout(function () {
                        d.html(S), n._updateFileDetails(f), a(e + 1)
                    }, 100), n._raise("fileloaded", [U, D, e, l])
                }, l.onprogress = function (i) {
                    if (i.lengthComputable) {
                        var t = i.loaded / i.total * 100, a = Math.ceil(t);
                        S = u.setTokens({index: e + 1, files: f, percent: a, name: B}), setTimeout(function () {
                            d.html(S)
                        }, 100)
                    }
                }, T = m.text, E = m.image, T(U.type, B) ? l.readAsText(U, n.textEncoding) : E(U.type, B) ? l.readAsDataURL(U) : l.readAsArrayBuffer(U)) : (n._previewDefault(U, D), setTimeout(function () {
                    a(e + 1), n._updateFileDetails(f)
                }, 100), n._raise("fileloaded", [U, D, e, l])), void n.addToStack(U)) : (n.isUploadable && n.addToStack(U), setTimeout(function () {
                    a(e + 1), n._updateFileDetails(f)
                }, 100), void n._raise("fileloaded", [U, D, e, l])) : (S = n.msgInvalidFileExtension.setTokens({
                    name: B,
                    extensions: _
                }), void(n.isError = y(S, U, D, e)))
            }, a(0), n._updateFileDetails(f, !1)
        }, _updateFileDetails: function (e) {
            var t = this, a = t.$element, n = t.getFileStack(),
                r = i.isIE(9) && i.findFileName(a.val()) || a[0].files[0] && a[0].files[0].name || n.length && n[0].name || "",
                o = t.slug(r), l = t.isUploadable ? n.length : e, s = t.previewCache.count() + l,
                d = l > 1 ? t._getMsgSelected(s) : o;
            t.isError ? (t.$previewContainer.removeClass("file-thumb-loading"), t.$previewStatus.html(""), t.$captionContainer.find(".kv-caption-icon").hide()) : t._showFileIcon(), t._setCaption(d, t.isError), t.$container.removeClass("file-input-new file-input-ajax-new"), 1 === arguments.length && t._raise("fileselect", [e, o]), t.previewCache.count() && t._initPreviewActions()
        }, _setThumbStatus: function (e, i) {
            var t = this;
            if (t.showPreview) {
                var a = "indicator" + i, n = a + "Title", r = "file-preview-" + i.toLowerCase(),
                    o = e.find(".file-upload-indicator"), l = t.fileActionSettings;
                e.removeClass("file-preview-success file-preview-error file-preview-loading"), "Error" === i && e.find(".kv-file-upload").attr("disabled", !0), "Success" === i && (e.find(".file-drag-handle").remove(), o.css("margin-left", 0)), o.html(l[a]), o.attr("title", l[n]), e.addClass(r)
            }
        }, _setProgressCancelled: function () {
            var e = this;
            e._setProgress(101, e.$progress, e.msgCancelled)
        }, _setProgress: function (e, t, a) {
            var n, r = this, o = Math.min(e, 100), l = r.progressUploadThreshold,
                s = 100 >= e ? r.progressTemplate : r.progressCompleteTemplate,
                d = 100 > o ? r.progressTemplate : a ? r.progressErrorTemplate : s;
            t = t || r.$progress, i.isEmpty(d) || (n = l && o > l && 100 >= e ? d.setTokens({
                percent: l,
                status: r.msgUploadThreshold
            }) : d.setTokens({
                percent: o,
                status: e > 100 ? r.msgUploadEnd : o + "%"
            }), t.html(n), a && t.find('[role="progressbar"]').html(a))
        }, _setFileDropZoneTitle: function () {
            var e, t = this, a = t.$container.find(".file-drop-zone"), n = t.dropZoneTitle;
            t.isClickable && (e = i.isEmpty(t.$element.attr("multiple")) ? t.fileSingle : t.filePlural, n += t.dropZoneClickTitle.replace("{files}", e)), a.find("." + t.dropZoneTitleClass).remove(), t.isUploadable && t.showPreview && 0 !== a.length && !(t.getFileStack().length > 0) && t.dropZoneEnabled && (0 === a.find(i.FRAMES).length && i.isEmpty(t.defaultPreviewContent) && a.prepend('<div class="' + t.dropZoneTitleClass + '">' + n + "</div>"), t.$container.removeClass("file-input-new"), i.addCss(t.$container, "file-input-ajax-new"))
        }, _setAsyncUploadStatus: function (i, t, a) {
            var n = this, r = 0;
            n._setProgress(t, e("#" + i).find(".file-thumb-progress")), n.uploadStatus[i] = t, e.each(n.uploadStatus, function (e, i) {
                r += i
            }), n._setProgress(Math.floor(r / a))
        }, _validateMinCount: function () {
            var e = this, i = e.isUploadable ? e.getFileStack().length : e.$element.get(0).files.length;
            return e.validateInitialCount && e.minFileCount > 0 && e._getFileCount(i - 1) < e.minFileCount ? (e._noFilesError({}), !1) : !0
        }, _getFileCount: function (e) {
            var i = this, t = 0;
            return i.validateInitialCount && !i.overwriteInitial && (t = i.previewCache.count(), e += t), e
        }, _getFileId: function (e) {
            var i, t = this, a = t.generateFileId;
            return "function" == typeof a ? a(e, event) : e ? (i = e.webkitRelativePath || e.fileName || e.name || null, i ? e.size + "-" + i.replace(/[^0-9a-zA-Z_-]/gim, "") : null) : null
        }, _getFileName: function (e) {
            return e && e.name ? this.slug(e.name) : void 0
        }, _getFileIds: function (e) {
            var i = this;
            return i.fileids.filter(function (i) {
                return e ? void 0 !== i : void 0 !== i && null !== i
            })
        }, _getFileNames: function (e) {
            var i = this;
            return i.filenames.filter(function (i) {
                return e ? void 0 !== i : void 0 !== i && null !== i
            })
        }, _setPreviewError: function (e, i, t) {
            var a = this;
            void 0 !== i && a.updateStack(i, t), a.removeFromPreviewOnError ? e.remove() : a._setThumbStatus(e, "Error")
        }, _checkDimensions: function (e, t, a, n, r, o, l) {
            var s, d, c, u, p = this, f = "Small" === t ? "min" : "max", m = p[f + "Image" + o];
            !i.isEmpty(m) && a.length && (c = a[0], d = "Width" === o ? c.naturalWidth || c.width : c.naturalHeight || c.height, u = "Small" === t ? d >= m : m >= d, u || (s = p["msgImage" + o + t].setTokens({
                name: r,
                size: m
            }), p._showUploadError(s, l), p._setPreviewError(n, e, null)))
        }, _validateImage: function (i, t, a, n, r) {
            var o, l, s, d, c = this, u = c.$preview, p = u.find("#" + i), f = p.attr("data-fileindex"),
                m = p.find("img");
            t = t || "Untitled", m.one("load", function () {
                l = p.width(), s = u.width(), l > s && m.css("width", "100%"), o = {
                    ind: f,
                    id: i
                }, c._checkDimensions(f, "Small", m, p, t, "Width", o), c._checkDimensions(f, "Small", m, p, t, "Height", o), c.resizeImage || (c._checkDimensions(f, "Large", m, p, t, "Width", o), c._checkDimensions(f, "Large", m, p, t, "Height", o)), c._raise("fileimageloaded", [i]), d = window.piexif ? window.piexif.load(r) : null, c.loadedImages.push({
                    ind: f,
                    img: m,
                    thumb: p,
                    pid: i,
                    typ: a,
                    siz: n,
                    validated: !1,
                    imgData: r,
                    exifObj: d
                }), p.data("exif", d), c._validateAllImages()
            }).one("error", function () {
                c._raise("fileimageloaderror", [i])
            }).each(function () {
                this.complete ? e(this).trigger("load") : this.error && e(this).trigger("error")
            })
        }, _validateAllImages: function () {
            var e, i, t, a = this, n = {val: 0}, r = a.loadedImages.length, o = a.resizeIfSizeMoreThan;
            if (r === a.totalImagesCount && (a._raise("fileimagesloaded"), a.resizeImage)) for (e = 0; e < a.loadedImages.length; e++) i = a.loadedImages[e], i.validated || (t = i.siz, t && t > 1e3 * o && a._getResizedImage(i, n, r), a.loadedImages[e].validated = !0)
        }, _getResizedImage: function (t, a, n) {
            var r, o, l, s, d, c, u, p = this, f = e(t.img)[0], m = f.naturalWidth, v = f.naturalHeight, h = 1,
                g = p.maxImageWidth || m, w = p.maxImageHeight || v, _ = !(!m || !v), b = p.imageCanvas,
                C = p.imageCanvasContext, y = t.typ, T = t.pid, E = t.ind, x = t.thumb, F = t.exifObj;
            if (d = function (e, i, t) {
                p.isUploadable ? p._showUploadError(e, i, t) : p._showError(e, i, t), p._setPreviewError(x, E)
            }, (!p.filestack[E] || !_ || g >= m && w >= v) && (_ && p.filestack[E] && p._raise("fileimageresized", [T, E]), a.val++, a.val === n && p._raise("fileimagesresized"), !_)) return void d(p.msgImageResizeError, {
                id: T,
                index: E
            }, "fileimageresizeerror");
            y = y || p.resizeDefaultImageType, o = m > g, l = v > w, h = "width" === p.resizePreference ? o ? g / m : l ? w / v : 1 : l ? w / v : o ? g / m : 1, p._resetCanvas(), m *= h, v *= h, b.width = m, b.height = v;
            try {
                C.drawImage(f, 0, 0, m, v), s = b.toDataURL(y, p.resizeQuality), F && (u = window.piexif.dump(F), s = window.piexif.insert(u, s)), r = i.dataURI2Blob(s), p.filestack[E] = r, p._raise("fileimageresized", [T, E]), a.val++, a.val === n && p._raise("fileimagesresized", [void 0, void 0]), r instanceof Blob || d(p.msgImageResizeError, {
                    id: T,
                    index: E
                }, "fileimageresizeerror")
            } catch (S) {
                a.val++, a.val === n && p._raise("fileimagesresized", [void 0, void 0]), c = p.msgImageResizeException.replace("{errors}", S.message), d(c, {
                    id: T,
                    index: E
                }, "fileimageresizeexception")
            }
        }, _initBrowse: function (e) {
            var i = this;
            i.showBrowse ? (i.$btnFile = e.find(".btn-file"), i.$btnFile.append(i.$element)) : i.$element.hide()
        }, _initCaption: function () {
            var e = this, t = e.initialCaption || "";
            return e.overwriteInitial || i.isEmpty(t) ? (e.$caption.html(""), !1) : (e._setCaption(t), !0)
        }, _setCaption: function (t, a) {
            var n, r, o, l, s = this, d = s.getFileStack();
            if (s.$caption.length) {
                if (a) n = e("<div>" + s.msgValidationError + "</div>").text(),
                    o = d.length, l = o ? 1 === o && d[0] ? s._getFileNames()[0] : s._getMsgSelected(o) : s._getMsgSelected(s.msgNo), r = '<span class="' + s.msgValidationErrorClass + '">' + s.msgValidationErrorIcon + (i.isEmpty(t) ? l : t) + "</span>"; else {
                    if (i.isEmpty(t)) return;
                    n = e("<div>" + t + "</div>").text(), r = s._getLayoutTemplate("fileIcon") + n
                }
                s.$caption.html(r), s.$caption.attr("title", n), s.$captionContainer.find(".file-caption-ellipsis").attr("title", n)
            }
        }, _createContainer: function () {
            var i = this, t = {"class": "file-input file-input-new" + (i.rtl ? " kv-rtl" : "")},
                a = e(document.createElement("div")).attr(t).html(i._renderMain());
            return i.$element.before(a), i._initBrowse(a), i.theme && a.addClass("theme-" + i.theme), a
        }, _refreshContainer: function () {
            var e = this, i = e.$container;
            i.before(e.$element), i.html(e._renderMain()), e._initBrowse(i)
        }, _renderMain: function () {
            var e = this, i = e.isUploadable && e.dropZoneEnabled ? " file-drop-zone" : "file-drop-disabled",
                t = e.showClose ? e._getLayoutTemplate("close") : "",
                a = e.showPreview ? e._getLayoutTemplate("preview").setTokens({
                    "class": e.previewClass,
                    dropClass: i
                }) : "", n = e.isDisabled ? e.captionClass + " file-caption-disabled" : e.captionClass,
                r = e.captionTemplate.setTokens({"class": n + " kv-fileinput-caption"});
            return e.mainTemplate.setTokens({
                "class": e.mainClass + (!e.showBrowse && e.showCaption ? " no-browse" : ""),
                preview: a,
                close: t,
                caption: r,
                upload: e._renderButton("upload"),
                remove: e._renderButton("remove"),
                cancel: e._renderButton("cancel"),
                browse: e._renderButton("browse")
            })
        }, _renderButton: function (e) {
            var t = this, a = t._getLayoutTemplate("btnDefault"), n = t[e + "Class"], r = t[e + "Title"],
                o = t[e + "Icon"], l = t[e + "Label"], s = t.isDisabled ? " disabled" : "", d = "button";
            switch (e) {
                case"remove":
                    if (!t.showRemove) return "";
                    break;
                case"cancel":
                    if (!t.showCancel) return "";
                    n += " hide";
                    break;
                case"upload":
                    if (!t.showUpload) return "";
                    t.isUploadable && !t.isDisabled ? a = t._getLayoutTemplate("btnLink").replace("{href}", t.uploadUrl) : d = "submit";
                    break;
                case"browse":
                    if (!t.showBrowse) return "";
                    a = t._getLayoutTemplate("btnBrowse");
                    break;
                default:
                    return ""
            }
            return n += "browse" === e ? " btn-file" : " fileinput-" + e + " fileinput-" + e + "-button", i.isEmpty(l) || (l = ' <span class="' + t.buttonLabelClass + '">' + l + "</span>"), a.setTokens({
                type: d,
                css: n,
                title: r,
                status: s,
                icon: o,
                label: l
            })
        }, _renderThumbProgress: function () {
            var e = this;
            return '<div class="file-thumb-progress hide">' + e.progressTemplate.setTokens({
                percent: "0",
                status: e.msgUploadBegin
            }) + "</div>"
        }, _renderFileFooter: function (e, t, a, n) {
            var r, o = this, l = o.fileActionSettings, s = l.showRemove, d = l.showDrag, c = l.showUpload,
                u = l.showZoom, p = o._getLayoutTemplate("footer"), f = n ? l.indicatorError : l.indicatorNew,
                m = o._getLayoutTemplate("indicator"), v = n ? l.indicatorErrorTitle : l.indicatorNewTitle,
                h = m.setTokens({indicator: f, indicatorTitle: v});
            return t = o._getSize(t), r = o.isUploadable ? p.setTokens({
                actions: o._renderFileActions(c, s, u, d, !1, !1, !1),
                caption: e,
                size: t,
                width: a,
                progress: o._renderThumbProgress(),
                indicator: h
            }) : p.setTokens({
                actions: o._renderFileActions(!1, !1, u, d, !1, !1, !1),
                caption: e,
                size: t,
                width: a,
                progress: "",
                indicator: h
            }), r = i.replaceTags(r, o.previewThumbTags)
        }, _renderFileActions: function (e, i, t, a, n, r, o, l) {
            if (!(e || i || t || a)) return "";
            var s, d = this, c = r === !1 ? "" : ' data-url="' + r + '"', u = o === !1 ? "" : ' data-key="' + o + '"',
                p = "", f = "", m = "", v = "", h = d._getLayoutTemplate("actions"), g = d.fileActionSettings,
                w = d.otherActionButtons.setTokens({dataKey: u}), _ = n ? g.removeClass + " disabled" : g.removeClass;
            return i && (p = d._getLayoutTemplate("actionDelete").setTokens({
                removeClass: _,
                removeIcon: g.removeIcon,
                removeTitle: g.removeTitle,
                dataUrl: c,
                dataKey: u
            })), e && (f = d._getLayoutTemplate("actionUpload").setTokens({
                uploadClass: g.uploadClass,
                uploadIcon: g.uploadIcon,
                uploadTitle: g.uploadTitle
            })), t && (m = d._getLayoutTemplate("actionZoom").setTokens({
                zoomClass: g.zoomClass,
                zoomIcon: g.zoomIcon,
                zoomTitle: g.zoomTitle
            })), a && l && (s = "drag-handle-init " + g.dragClass, v = d._getLayoutTemplate("actionDrag").setTokens({
                dragClass: s,
                dragTitle: g.dragTitle,
                dragIcon: g.dragIcon
            })), h.setTokens({"delete": p, upload: f, zoom: m, drag: v, other: w})
        }, _browse: function (e) {
            var i = this;
            i._raise("filebrowse"), e && e.isDefaultPrevented() || (i.isError && !i.isUploadable && i.clear(), i.$captionContainer.focus())
        }, _filterDuplicate: function (e, i, t) {
            var a = this, n = a._getFileId(e);
            n && t && t.indexOf(n) > -1 || (t || (t = []), i.push(e), t.push(n))
        }, _change: function (t) {
            var a = this, n = a.$element;
            if (!a.isUploadable && i.isEmpty(n.val()) && a.fileInputCleared) return void(a.fileInputCleared = !1);
            a.fileInputCleared = !1;
            var r, o, l, s, d = [], c = arguments.length > 1, u = a.isUploadable,
                p = c ? t.originalEvent.dataTransfer.files : n.get(0).files, f = a.filestack.length,
                m = i.isEmpty(n.attr("multiple")), v = m && f > 0, h = 0, g = a._getFileIds(),
                w = function (i, t, n, r) {
                    var o = e.extend(!0, {}, a._getOutData({}, {}, p), {id: n, index: r}),
                        l = {id: n, index: r, file: t, files: p};
                    return a.isUploadable ? a._showUploadError(i, o) : a._showError(i, l)
                };
            if (a.reader = null, a._resetUpload(), a._hideFileIcon(), a.isUploadable && a.$container.find(".file-drop-zone ." + a.dropZoneTitleClass).remove(), c ? e.each(p, function (e, i) {
                i && !i.type && void 0 !== i.size && i.size % 4096 === 0 ? h++ : a._filterDuplicate(i, d, g)
            }) : (p = t.target && void 0 === t.target.files ? t.target.value ? [{name: t.target.value.replace(/^.+\\/, "")}] : [] : t.target.files || {}, u ? e.each(p, function (e, i) {
                a._filterDuplicate(i, d, g)
            }) : d = p), i.isEmpty(d) || 0 === d.length) return u || a.clear(), a._showFolderError(h), void a._raise("fileselectnone");
            if (a._resetErrors(), s = d.length, o = a._getFileCount(a.isUploadable ? a.getFileStack().length + s : s), a.maxFileCount > 0 && o > a.maxFileCount) {
                if (!a.autoReplace || s > a.maxFileCount) return l = a.autoReplace && s > a.maxFileCount ? s : o, r = a.msgFilesTooMany.replace("{m}", a.maxFileCount).replace("{n}", l), a.isError = w(r, null, null, null), a.$captionContainer.find(".kv-caption-icon").hide(), a._setCaption("", !0), void a.$container.removeClass("file-input-new file-input-ajax-new");
                o > a.maxFileCount && a._resetPreviewThumbs(u)
            } else !u || v ? (a._resetPreviewThumbs(!1), v && a.clearStack()) : !u || 0 !== f || a.previewCache.count() && !a.overwriteInitial || a._resetPreviewThumbs(!0);
            a.isPreviewable ? a._readFiles(d) : a._updateFileDetails(1), a._showFolderError(h)
        }, _abort: function (i) {
            var t, a = this;
            return a.ajaxAborted && "object" == typeof a.ajaxAborted && void 0 !== a.ajaxAborted.message ? (t = e.extend(!0, {}, a._getOutData(), i), t.abortData = a.ajaxAborted.data || {}, t.abortMessage = a.ajaxAborted.message, a._setProgress(101, a.$progress, a.msgCancelled), a._showUploadError(a.ajaxAborted.message, t, "filecustomerror"), a.cancel(), !0) : !1
        }, _resetFileStack: function () {
            var t = this, a = 0, n = [], r = [], o = [];
            t._getThumbs().each(function () {
                var l, s = e(this), d = s.attr("data-fileindex"), c = t.filestack[d], u = s.attr("id");
                "-1" !== d && -1 !== d && (void 0 !== c ? (n[a] = c, r[a] = t._getFileName(c), o[a] = t._getFileId(c), s.attr({
                    id: t.previewInitId + "-" + a,
                    "data-fileindex": a
                }), a++) : (l = "uploaded-" + i.uniqId(), s.attr({
                    id: l,
                    "data-fileindex": "-1"
                }), t.$preview.find("#zoom-" + u).attr("id", "zoom-" + l)))
            }), t.filestack = n, t.filenames = r, t.fileids = o
        }, _isFileSelectionValid: function (e) {
            var i = this;
            return e = e || 0, i.required && !i.getFilesCount() ? (i.$errorContainer.html(""), i._showUploadError(i.msgFileRequired), !1) : i.minFileCount > 0 && i._getFileCount(e) < i.minFileCount ? (i._noFilesError({}), !1) : !0
        }, clearStack: function () {
            var e = this;
            return e.filestack = [], e.filenames = [], e.fileids = [], e.$element
        }, updateStack: function (e, i) {
            var t = this;
            return t.filestack[e] = i, t.filenames[e] = t._getFileName(i), t.fileids[e] = i && t._getFileId(i) || null, t.$element
        }, addToStack: function (e) {
            var i = this;
            return i.filestack.push(e), i.filenames.push(i._getFileName(e)), i.fileids.push(i._getFileId(e)), i.$element
        }, getFileStack: function (e) {
            var i = this;
            return i.filestack.filter(function (i) {
                return e ? void 0 !== i : void 0 !== i && null !== i
            })
        }, getFilesCount: function () {
            var e = this, i = e.isUploadable ? e.getFileStack().length : e.$element.get(0).files.length;
            return e._getFileCount(i)
        }, lock: function () {
            var e = this;
            return e._resetErrors(), e.disable(), e.showRemove && i.addCss(e.$container.find(".fileinput-remove"), "hide"), e.showCancel && e.$container.find(".fileinput-cancel").removeClass("hide"), e._raise("filelock", [e.filestack, e._getExtraData()]), e.$element
        }, unlock: function (e) {
            var t = this;
            return void 0 === e && (e = !0), t.enable(), t.showCancel && i.addCss(t.$container.find(".fileinput-cancel"), "hide"), t.showRemove && t.$container.find(".fileinput-remove").removeClass("hide"), e && t._resetFileStack(), t._raise("fileunlock", [t.filestack, t._getExtraData()]), t.$element
        }, cancel: function () {
            var i, t = this, a = t.ajaxRequests, n = a.length;
            if (n > 0) for (i = 0; n > i; i += 1) t.cancelling = !0, a[i].abort();
            return t._setProgressCancelled(), t._getThumbs().each(function () {
                var i = e(this), a = i.attr("data-fileindex");
                i.removeClass("file-uploading"), void 0 !== t.filestack[a] && (i.find(".kv-file-upload").removeClass("disabled").removeAttr("disabled"), i.find(".kv-file-remove").removeClass("disabled").removeAttr("disabled")), t.unlock()
            }), t.$element
        }, clear: function () {
            var t, a = this;
            if (a._raise("fileclear")) return a.$btnUpload.removeAttr("disabled"), a._getThumbs().find("video,audio,img").each(function () {
                i.cleanMemory(e(this))
            }), a._resetUpload(), a.clearStack(), a._clearFileInput(), a._resetErrors(!0), a._hasInitialPreview() ? (a._showFileIcon(), a._resetPreview(), a._initPreviewActions(), a.$container.removeClass("file-input-new")) : (a._getThumbs().each(function () {
                a._clearObjects(e(this))
            }), a.isUploadable && (a.previewCache.data = {}), a.$preview.html(""), t = !a.overwriteInitial && a.initialCaption.length > 0 ? a.initialCaption : "", a.$caption.html(t), a.$caption.attr("title", ""), i.addCss(a.$container, "file-input-new"), a._validateDefaultPreview()), 0 === a.$container.find(i.FRAMES).length && (a._initCaption() || a.$captionContainer.find(".kv-caption-icon").hide()), a._hideFileIcon(), a._raise("filecleared"), a.$captionContainer.focus(), a._setFileDropZoneTitle(), a.$element
        }, reset: function () {
            var e = this;
            if (e._raise("filereset")) return e._resetPreview(), e.$container.find(".fileinput-filename").text(""), i.addCss(e.$container, "file-input-new"), (e.getFrames().length || e.isUploadable && e.dropZoneEnabled) && e.$container.removeClass("file-input-new"), e._setFileDropZoneTitle(), e.clearStack(), e.formdata = {}, e.$element
        }, disable: function () {
            var e = this;
            return e.isDisabled = !0, e._raise("filedisabled"), e.$element.attr("disabled", "disabled"), e.$container.find(".kv-fileinput-caption").addClass("file-caption-disabled"), e.$container.find(".btn-file, .fileinput-remove, .fileinput-upload, .file-preview-frame button").attr("disabled", !0), e._initDragDrop(), e.$element
        }, enable: function () {
            var e = this;
            return e.isDisabled = !1, e._raise("fileenabled"), e.$element.removeAttr("disabled"), e.$container.find(".kv-fileinput-caption").removeClass("file-caption-disabled"), e.$container.find(".btn-file, .fileinput-remove, .fileinput-upload, .file-preview-frame button").removeAttr("disabled"), e._initDragDrop(), e.$element
        }, upload: function () {
            var t, a, n, r = this, o = r.getFileStack().length, l = !e.isEmptyObject(r._getExtraData());
            if (r.isUploadable && !r.isDisabled && r._isFileSelectionValid(o)) {
                if (r._resetUpload(), 0 === o && !l) return void r._showUploadError(r.msgUploadEmpty);
                if (r.$progress.removeClass("hide"), r.uploadCount = 0, r.uploadStatus = {}, r.uploadLog = [], r.lock(), r._setProgress(2), 0 === o && l) return void r._uploadExtraOnly();
                if (n = r.filestack.length, r.hasInitData = !1, !r.uploadAsync) return r._uploadBatch(), r.$element;
                for (a = r._getOutData(), r._raise("filebatchpreupload", [a]), r.fileBatchCompleted = !1, r.uploadCache = {
                    content: [],
                    config: [],
                    tags: [],
                    append: !0
                }, r.uploadAsyncCount = r.getFileStack().length, t = 0; n > t; t++) r.uploadCache.content[t] = null, r.uploadCache.config[t] = null, r.uploadCache.tags[t] = null;
                for (r.$preview.find(".file-preview-initial").removeClass(i.SORT_CSS), r._initSortable(), r.cacheInitialPreview = r.getPreview(), t = 0; n > t; t++) void 0 !== r.filestack[t] && r._uploadSingle(t, r.filestack, !0)
            }
        }, destroy: function () {
            var i = this, t = i.$form, a = i.$container, n = i.$element, r = i.namespace;
            return e(document).off(r), e(window).off(r), t && t.length && t.off(r), i.isUploadable && i._clearFileInput(), i._cleanup(), i._initPreviewCache(), n.insertBefore(a).off(r).removeData(), a.off().remove(), n
        }, refresh: function (i) {
            var t = this, a = t.$element;
            return i = i ? e.extend(!0, {}, t.options, i) : t.options, t.destroy(), a.fileinput(i), t = a.data("fileinput"), t.isUploadable && t._clearFileInput(), a.val() && a.trigger("change.fileinput"), a
        }, zoom: function (e) {
            var t = this, a = t._getFrame(e), n = t.$modal;
            a && (i.initModal(n), n.html(t._getModalContent()), t._setZoomContent(a), n.modal("show"), t._initZoomButtons())
        }, getExif: function (e) {
            var i = this, t = i._getFrame(e);
            return t && t.data("exif") || null
        }, getFrames: function (e) {
            var t = this;
            return e = e || "", t.$preview.find(i.FRAMES + e)
        }, getPreview: function () {
            var e = this;
            return {content: e.initialPreview, config: e.initialPreviewConfig, tags: e.initialPreviewThumbTags}
        }
    }, e.fn.fileinput = function (a) {
        if (i.hasFileAPISupport() || i.isIE(9)) {
            var n = Array.apply(null, arguments), r = [];
            switch (n.shift(), this.each(function () {
                var o, l = e(this), s = l.data("fileinput"), d = "object" == typeof a && a,
                    c = d.theme || l.data("theme"), u = {}, p = {},
                    f = d.language || l.data("language") || e.fn.fileinput.defaults.language || "en";
                s || (c && (p = e.fn.fileinputThemes[c] || {}), "en" === f || i.isEmpty(e.fn.fileinputLocales[f]) || (u = e.fn.fileinputLocales[f] || {}), o = e.extend(!0, {}, e.fn.fileinput.defaults, p, e.fn.fileinputLocales.en, u, d, l.data()), s = new t(this, o), l.data("fileinput", s)), "string" == typeof a && r.push(s[a].apply(s, n))
            }), r.length) {
                case 0:
                    return this;
                case 1:
                    return r[0];
                default:
                    return r
            }
        }
    }, e.fn.fileinput.defaults = {
        language: "en",
        showCaption: !0,
        showBrowse: !0,
        showPreview: !0,
        showRemove: !0,
        showUpload: !0,
        showCancel: !0,
        showClose: !0,
        showUploadedThumbs: !0,
        browseOnZoneClick: !1,
        autoReplace: !1,
        autoOrientImage: !0,
        required: !1,
        rtl: !1,
        hideThumbnailContent: !1,
        generateFileId: null,
        previewClass: "",
        captionClass: "",
        frameClass: "krajee-default",
        mainClass: "file-caption-main",
        mainTemplate: null,
        purifyHtml: !0,
        fileSizeGetter: null,
        initialCaption: "",
        initialPreview: [],
        initialPreviewDelimiter: "*$$*",
        initialPreviewAsData: !1,
        initialPreviewFileType: "image",
        initialPreviewConfig: [],
        initialPreviewThumbTags: [],
        previewThumbTags: {},
        initialPreviewShowDelete: !0,
        removeFromPreviewOnError: !1,
        deleteUrl: "",
        deleteExtraData: {},
        overwriteInitial: !0,
        previewZoomButtonIcons: {
            prev: '<i class="glyphicon glyphicon-triangle-left"></i>',
            next: '<i class="glyphicon glyphicon-triangle-right"></i>',
            toggleheader: '<i class="glyphicon glyphicon-resize-vertical"></i>',
            fullscreen: '<i class="glyphicon glyphicon-fullscreen"></i>',
            borderless: '<i class="glyphicon glyphicon-resize-full"></i>',
            close: '<i class="glyphicon glyphicon-remove"></i>'
        },
        previewZoomButtonClasses: {
            prev: "btn btn-navigate",
            next: "btn btn-navigate",
            toggleheader: "btn btn-default btn-header-toggle",
            fullscreen: "btn btn-default",
            borderless: "btn btn-default",
            close: "btn btn-default"
        },
        preferIconicPreview: !1,
        preferIconicZoomPreview: !1,
        allowedPreviewTypes: void 0,
        allowedPreviewMimeTypes: null,
        allowedFileTypes: null,
        allowedFileExtensions: null,
        defaultPreviewContent: null,
        customLayoutTags: {},
        customPreviewTags: {},
        previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
        previewFileIconClass: "file-other-icon",
        previewFileIconSettings: {},
        previewFileExtSettings: {},
        buttonLabelClass: "hidden-xs",
        browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>&nbsp;',
        browseClass: "btn btn-primary",
        removeIcon: '<i class="glyphicon glyphicon-trash"></i>',
        removeClass: "btn btn-default",
        cancelIcon: '<i class="glyphicon glyphicon-ban-circle"></i>',
        cancelClass: "btn btn-default",
        uploadIcon: '<i class="glyphicon glyphicon-upload"></i>',
        uploadClass: "btn btn-default",
        uploadUrl: null,
        uploadAsync: !0,
        uploadExtraData: {},
        zoomModalHeight: 480,
        minImageWidth: null,
        minImageHeight: null,
        maxImageWidth: null,
        maxImageHeight: null,
        resizeImage: !1,
        resizePreference: "width",
        resizeQuality: .92,
        resizeDefaultImageType: "image/jpeg",
        resizeIfSizeMoreThan: 0,
        minFileSize: 0,
        maxFileSize: 0,
        maxFilePreviewSize: 25600,
        minFileCount: 0,
        maxFileCount: 0,
        validateInitialCount: !1,
        msgValidationErrorClass: "text-danger",
        msgValidationErrorIcon: '<i class="glyphicon glyphicon-exclamation-sign"></i> ',
        msgErrorClass: "file-error-message",
        progressThumbClass: "progress-bar progress-bar-success progress-bar-striped active",
        progressClass: "progress-bar progress-bar-success progress-bar-striped active",
        progressCompleteClass: "progress-bar progress-bar-success",
        progressErrorClass: "progress-bar progress-bar-danger",
        progressUploadThreshold: 99,
        previewFileType: "image",
        elCaptionContainer: null,
        elCaptionText: null,
        elPreviewContainer: null,
        elPreviewImage: null,
        elPreviewStatus: null,
        elErrorContainer: null,
        errorCloseButton: '<span class="close kv-error-close">&times;</span>',
        slugCallback: null,
        dropZoneEnabled: !0,
        dropZoneTitleClass: "file-drop-zone-title",
        fileActionSettings: {},
        otherActionButtons: "",
        textEncoding: "UTF-8",
        ajaxSettings: {},
        ajaxDeleteSettings: {},
        showAjaxErrorDetails: !0
    }, e.fn.fileinputLocales.en = {
        fileSingle: "file",
        filePlural: "files",
        browseLabel: "Browse &hellip;",
        removeLabel: "Remove",
        removeTitle: "Clear selected files",
        cancelLabel: "Cancel",
        cancelTitle: "Abort ongoing upload",
        uploadLabel: "Upload",
        uploadTitle: "Upload selected files",
        msgNo: "No",
        msgNoFilesSelected: "No files selected",
        msgCancelled: "Cancelled",
        msgZoomModalHeading: "Detailed Preview",
        msgFileRequired: "You must select a file to upload.",
        msgSizeTooSmall: 'File "{name}" (<b>{size} KB</b>) is too small and must be larger than <b>{minSize} KB</b>.',
        msgSizeTooLarge: 'File "{name}" (<b>{size} KB</b>) exceeds maximum allowed upload size of <b>{maxSize} KB</b>.',
        msgFilesTooLess: "You must select at least <b>{n}</b> {files} to upload.",
        msgFilesTooMany: "Number of files selected for upload <b>({n})</b> exceeds maximum allowed limit of <b>{m}</b>.",
        msgFileNotFound: 'File "{name}" not found!',
        msgFileSecured: 'Security restrictions prevent reading the file "{name}".',
        msgFileNotReadable: 'File "{name}" is not readable.',
        msgFilePreviewAborted: 'File preview aborted for "{name}".',
        msgFilePreviewError: 'An error occurred while reading the file "{name}".',
        msgInvalidFileName: 'Invalid or unsupported characters in file name "{name}".',
        msgInvalidFileType: 'Invalid type for file "{name}". Only "{types}" files are supported.',
        msgInvalidFileExtension: 'Invalid extension for file "{name}". Only "{extensions}" files are supported.',
        msgFileTypes: {
            image: "image",
            html: "HTML",
            text: "text",
            video: "video",
            audio: "audio",
            flash: "flash",
            pdf: "PDF",
            object: "object"
        },
        msgUploadAborted: "The file upload was aborted",
        msgUploadThreshold: "Processing...",
        msgUploadBegin: "Initializing...",
        msgUploadEnd: "Done",
        msgUploadEmpty: "No valid data available for upload.",
        msgValidationError: "Validation Error",
        msgLoading: "Loading file {index} of {files} &hellip;",
        msgProgress: "Loading file {index} of {files} - {name} - {percent}% completed.",
        msgSelected: "{n} {files} selected",
        msgFoldersNotAllowed: "Drag & drop files only! {n} folder(s) dropped were skipped.",
        msgImageWidthSmall: 'Width of image file "{name}" must be at least {size} px.',
        msgImageHeightSmall: 'Height of image file "{name}" must be at least {size} px.',
        msgImageWidthLarge: 'Width of image file "{name}" cannot exceed {size} px.',
        msgImageHeightLarge: 'Height of image file "{name}" cannot exceed {size} px.',
        msgImageResizeError: "Could not get the image dimensions to resize.",
        msgImageResizeException: "Error while resizing the image.<pre>{errors}</pre>",
        msgAjaxError: "Something went wrong with the {operation} operation. Please try again later!",
        msgAjaxProgressError: "{operation} failed",
        ajaxOperations: {
            deleteThumb: "file delete",
            uploadThumb: "file upload",
            uploadBatch: "batch file upload",
            uploadExtra: "form data upload"
        },
        dropZoneTitle: "Drag & drop files here &hellip;",
        dropZoneClickTitle: "<br>(or click to select {files})",
        previewZoomButtonTitles: {
            prev: "View previous file",
            next: "View next file",
            toggleheader: "Toggle header",
            fullscreen: "Toggle full screen",
            borderless: "Toggle borderless mode",
            close: "Close detailed preview"
        }
    }, e.fn.fileinput.Constructor = t, e(document).ready(function () {
        var i = e("input.file[type=file]");
        i.length && i.fileinput()
    })
});