# Visualization-Tool-for-SPDY-QUIC-vs-HTTP

Though QUIC and SPDY packets were introduced a year ago, not much is known
about how many of our favourite websites use such protocols to increase user experience. In
this project, we have tried to find out details of packets received from various websites by
monitoring it through Wireshark and giving them as input to our tool. Since QUIC is only
supported by few browser, it enables us to analyse difference in performance of QUIC and
SPDY while using browsers which support QUIC and browsers which doesn’t. We have
presented few comparisons between SPDY and HTTP, QUIC and HTTP. Different metrics
which are used here are Throughput per sec, Page load time, Forward Throughput. It was
observed that throughput for HTTP is than that of SPDY while page load time of SPDY is more
than HTTP. When compared to HTTP, QUIC was observed to have slightly higher throughput
and lower page load time.
